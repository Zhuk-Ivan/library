package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.converter.UserConverter;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.Role;
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
        try (final Session session = HibernateUtil.getSession()){
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
    } catch (
    RollbackException e) {
    }

    }

    @Override
    public void deleteUser(String login) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from UserEntity u where u.login = :login")
                .setParameter("login", login)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Role roleU = Role.USER;
        Role roleB = Role.BLOCKED;
       final List<UserEntity> users = HibernateUtil.getSession().createQuery("from UserEntity u where u.role = :roleU or u.role = :roleB")
               .setParameter("roleU", roleU)
               .setParameter("roleB", roleB)
               .list();
       return users.stream().map(UserConverter::fromEntity)
               .collect(Collectors.toList());
    }


    @Override
    public void changePersonalData(User user) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update UserEntity u set u.firstName= :firstName, u.lastName= :lastName, u.phone= :phone, u.email= :email, u.password= :password")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("phone", user.getPhone())
                .setParameter("email",user.getEmail())
                .setParameter("password",user.getPassword())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void blockUser(Role role, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update UserEntity u set u.role = :role where u.id = :id")
                .setParameter("role", role)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }



    @Override
    public User showUser(String login) {
        UserEntity user;
        try {
            user = (UserEntity) HibernateUtil.getSession().createQuery("from UserEntity ue where ue.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            user = null;
        }

        return UserConverter.fromEntity(user);
    }
}

