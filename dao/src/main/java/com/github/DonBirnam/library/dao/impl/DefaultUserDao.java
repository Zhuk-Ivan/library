package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.converter.AuthUserConverter;
import com.github.DonBirnam.library.dao.converter.UserConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;
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
    public Long saveUser(UserRegDTO userRegDTO) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(userRegDTO);
        UserEntity userEntity = UserConverter.toEntity(userRegDTO);

        authUserEntity.setUserEntity(userEntity);
        userEntity.setAuthUserEntity(authUserEntity);
        try (final Session session = HibernateUtil.getSession()){
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
    } catch (
    RollbackException e) {
    }
        return authUserEntity.getId();
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
    public List<UserDTO> getAllUsers() {
        Role roleU = Role.USER;
        Role roleB = Role.BLOCKED;
       final List<UserEntity> users = HibernateUtil.getSession().createQuery("from UserEntity u where u.authUserEntity.role = :roleU or u.authUserEntity.role = :roleB")
               .setParameter("roleU", roleU)
               .setParameter("roleB", roleB)
               .list();
       return users.stream().map(UserConverter::fromEntity)
               .collect(Collectors.toList());
    }


    @Override
    public void changePersonalData(UserDTO userDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update UserEntity u set u.firstName= :firstName, u.lastName= :lastName, u.phone= :phone, u.email= :email, u.password= :password")
                .setParameter("firstName", userDTO.getFirstName())
                .setParameter("lastName", userDTO.getLastName())
                .setParameter("phone", userDTO.getPhone())
                .setParameter("email", userDTO.getEmail())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void blockUser(Role role, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update AuthUserEntity u set u.role = :role where u.id = :id")
                .setParameter("role", role)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }



    @Override
    public UserDTO showUser(String login) {
        UserEntity user;
        try {
            user = (UserEntity) HibernateUtil.getSession().createQuery("from UserEntity eu where eu.authUserEntity.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            user = null;
        }

        return UserConverter.fromEntity(user);
    }

    @Override
    public UserDTO getById(Long id) {
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
}

