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
        final List<BookEntity> books = HibernateUtil.getSession().createQuery("from BookEntity")
                .list();
        return books.stream().map(BookConverter::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        final List<BookEntity> books = HibernateUtil.getSession().createQuery("from BookEntity b where b.genre = :genre")
                .setParameter("genre", genre)
                .list();
        return books.stream().map(BookConverter::fromEntity)
                .collect(Collectors.toList());
    }
}


