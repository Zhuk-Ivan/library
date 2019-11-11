package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.converter.AuthUserConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.Role;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

public class DefaultAuthUserDao implements AuthUserDao{

    private static class SingletonHolder {
        static final AuthUserDao HOLDER_INSTANCE = new DefaultAuthUserDao();
    }

    public static AuthUserDao getInstance() {
        return DefaultAuthUserDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Long saveAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        try ( Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(authUserEntity);
            session.getTransaction().commit();
        } catch (
                RollbackException e) {
            return null;
        }
        return authUserEntity.getId();
    }

    @Override
    public void changeAuthUserPass(String password, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update AuthUserEntity u set u.password = :password where u.id = :id")
                .setParameter("password", password)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public AuthUser getById(Long id) {
        AuthUserEntity authUserEntity;
        try {
            authUserEntity = (AuthUserEntity) HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            authUserEntity = null;
        }

        return AuthUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public AuthUser getByLogin(String login) {
        AuthUserEntity authUserEntity;
        try {
            authUserEntity = (AuthUserEntity) HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            authUserEntity = null;
        }

        return AuthUserConverter.fromEntity(authUserEntity);
    }
    @Override
    public void deleteAuthUser(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from AuthUserEntity u where u.id = :id")
                .setParameter("id", id)
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

//    @Override
//    public List<AuthUser> getAllUsers() {
//        Role roleU = Role.USER;
//        Role roleB = Role.BLOCKED;
//        final List<AuthUserEntity> users = HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.role = :roleU or au.role = :roleB")
//                .setParameter("roleU", roleU)
//                .setParameter("roleB", roleB)
//                .list();
//        return users.stream().map(AuthUserConverter::fromEntity)
//                .collect(Collectors.toList());
//
//    }

}
