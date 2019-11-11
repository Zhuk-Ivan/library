package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.converter.AuthorConverter;
import com.github.DonBirnam.library.dao.converter.BookConverter;
import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
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
    public Long createBook(Book book) {
        BookEntity bookEntity = BookConverter.toEntity(book);
        try (final Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(bookEntity);
            session.getTransaction().commit();
        } catch (
                RollbackException e) {
            return null;
        }
        return bookEntity.getId();
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
        bookEntityUPD.setInStock(book.getInStock());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBookStatus(BookStatus status, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update BookEntity u set u.status= :status where u.id = :id")
                .setParameter("status", status)
                .setParameter("id", id)
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

//    @Override
//    public List<Book> getAllBooks(Integer page) {
//        Integer pageSize = 2;
//        Session session = HibernateUtil.getSession();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<BookEntity> criteria = cb.createQuery(BookEntity.class);
//        criteria.select(criteria.from(BookEntity.class));
//        TypedQuery<BookEntity> typedQuery = session.createQuery(criteria);
//        typedQuery.setFirstResult(page);
//        typedQuery.setMaxResults(pageSize * page);
//        List<BookEntity> bookEntities = typedQuery.getResultList();
//        if (bookEntities.size() > 0) {
//            return bookEntities.stream().map(BookConverter::fromEntity).collect(Collectors.toList());
//        }
//        return null;
//    }

    @Override
    public List<Book> getAllBooks() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteria = cb.createQuery(BookEntity.class);
        criteria.select(criteria.from(BookEntity.class));
        List<BookEntity> bookEntities = session.createQuery(criteria).getResultList();
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
    public int countBooks() {
         List<BookEntity> books = HibernateUtil.getSession().createQuery("from BookEntity")
                .list();
        int bookSize = books.size();
        return bookSize;
    }
}

