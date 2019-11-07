package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.converter.AuthorConverter;
import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import com.github.DonBirnam.library.model.Author;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAuthorDao implements AuthorDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final AuthorDao HOLDER_INSTANCE = new DefaultAuthorDao();
    }

    public static AuthorDao getInstance(){
        return DefaultAuthorDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void createAuthor(Author author) {
        AuthorEntity authorEntity = AuthorConverter.toEntity(author);
        try (final Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(authorEntity);
            session.getTransaction().commit();
        } catch (RollbackException e){

        }

    }

    @Override
    public Author findById(Long id) {
        AuthorEntity author;
        try {
            author = (AuthorEntity) HibernateUtil.getSession().createQuery("from AuthorEntity a where a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            author = null;
        }

        return AuthorConverter.fromEntity(author);
    }


    @Override
    public Author findByName(String lastName) {
        AuthorEntity author;
        try {
            author = (AuthorEntity) HibernateUtil.getSession().createQuery("from AuthorEntity a where a.lastName = :lastName")
                    .setParameter("lastName", lastName)
                    .getSingleResult();
        } catch (NoResultException e){
            author = null;
        }

        return AuthorConverter.fromEntity(author);
    }



    @Override
    public void updateAuthor(Author author) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update AuthorEntity a set a.firstName= :firstName, a.lastName= :lastName where a.id = :id")
                .setParameter("firstName", author.getFirstName())
                .setParameter("lastName", author.getLastName())
                .setParameter("id", author.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAuthor(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
        session.remove(authorEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Author> getAllAuthors() {
         List<AuthorEntity> authors = HibernateUtil.getSession().createQuery("from AuthorEntity")
                .list();
        return authors.stream().map(AuthorConverter::fromEntity)
                .collect(Collectors.toList());

    }
}
