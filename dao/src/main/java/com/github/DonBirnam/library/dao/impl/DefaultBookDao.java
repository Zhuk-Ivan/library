package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.converter.AuthorConverter;
import com.github.DonBirnam.library.dao.converter.BookConverter;
import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultBookDao implements BookDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final BookDao HOLDER_INSTANCE = new DefaultBookDao();
    }

    public static BookDao getInstance() {
        return DefaultBookDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void createBook(Book book) {
        BookEntity bookEntity = BookConverter.toEntity(book);
        try (final Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(bookEntity);
            session.getTransaction().commit();
        } catch (
                RollbackException e) {
        }

    }

    @Override
    public Book findById(Long id) {
        BookEntity book;
        try {
            book = (BookEntity) HibernateUtil.getSession().createQuery("from BookEntity b where b.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            book = null;
        }

        return BookConverter.fromEntity(book);
    }

    @Override
    public Book findByTitle(String title) {
        BookEntity book;
        try {
            book = (BookEntity) HibernateUtil.getSession().createQuery("from BookEntity b where b.title = :title")
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            book = null;
        }

        return BookConverter.fromEntity(book);
    }


    @Override
    public void updateBook(Book book) {
        Session session = HibernateUtil.getSession();
        BookEntity bookEntityUPD = session.get(BookEntity.class, book.getId());
        session.beginTransaction();
        bookEntityUPD.setTitle(book.getTitle());
        bookEntityUPD.setAuthorEntity(AuthorConverter.toEntity(book.getAuthor()));
        bookEntityUPD.setPageCount(book.getPageCount());
        bookEntityUPD.setIsbn(book.getIsbn());
        bookEntityUPD.setGenre(book.getGenre());
        bookEntityUPD.setStatus(book.getStatus());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBookStatus(BookStatus status, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update BookEntity u set u.status= :status where u.id = :id")
                .setParameter("status", status)
                .setParameter("is", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteBook(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        BookEntity bookEntity = session.find(BookEntity.class, id);
        session.remove(bookEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Book> getAllBooks() {

        EntityManager em = HibernateUtil.getSession();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteria = cb.createQuery(BookEntity.class);
        criteria.select(criteria.from(BookEntity.class));
        List<BookEntity> bookEntities = em.createQuery(criteria).getResultList();
        return bookEntities.stream().map(BookConverter::fromEntity).collect(Collectors.toList());
    }




    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        final List<BookEntity> books = HibernateUtil.getSession().createQuery("from BookEntity b where b.genre = :genre")
                .setParameter("genre", genre)
                .list();
        return books.stream().map(BookConverter::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void addBookToOrder(Long orderId, String bookId) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from BookEntity b where b.id = :id");
        query.setParameter("id", bookId);
        BookEntity bookEntity = (BookEntity) query.getSingleResult();

        OrderEntity orderEntity = session.get(OrderEntity.class, orderId);

        orderEntity.getBooks().add(bookEntity);
        bookEntity.getOrders().add(orderEntity);

        try {
            session.beginTransaction();
            session.saveOrUpdate(orderEntity);
            session.getTransaction().commit();
        } catch (RollbackException e) {

        } finally {
            session.close();
        }
    }
}

