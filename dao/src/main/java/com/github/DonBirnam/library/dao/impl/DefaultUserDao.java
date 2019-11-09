package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.converter.UserConverter;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;


public class DefaultUserDao implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void saveUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        try (final Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(userEntity);
            session.getTransaction().commit();
        } catch (
                RollbackException e) {
        }
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public User getById(Long id) {
        UserEntity user;
        try {
            user = (UserEntity) HibernateUtil.getSession().createQuery("from UserEntity eu where eu.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            user = null;
        }

        return UserConverter.fromEntity(user);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from UserEntity u where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        final List<UserEntity> users = HibernateUtil.getSession().createQuery("from UserEntity").list();
        return users.stream().map(UserConverter::fromEntity)
                .collect(Collectors.toList());

    }

}
//    @Override
//    public void blockUser(Role role, Long id) {
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//        session.createQuery("update AuthUserEntity u set u.role = :role where u.id = :id")
//                .setParameter("role", role)
//                .setParameter("id", id)
//                .executeUpdate();
//        session.getTransaction().commit();
//        session.close();
//
//    }
//


