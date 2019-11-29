package com.github.DonBirnam.dao;

import com.github.DonBirnam.library.dao.AuthUserDao;

import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;

import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.AuthUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthUserTest {
    private AuthUserDao dao = DefaultAuthUserDao.getInstance();


    @Test
    void saveAuthUser() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUserEntity authUserEntity = HibernateUtil.getSession().get(AuthUserEntity.class, id);
        assertNotNull(authUserEntity);
        assertEquals(authUserEntity.getLogin(), "TestUser");
        dao.deleteAuthUser(id);
    }

    @Test
    void getByLogin(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getByLogin("TestUser");

        assertNotNull(authUser);
        assertEquals(authUser.getPassword(), "56789");
        dao.deleteAuthUser(id);
    }

    @Test
    void getById(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getById(id);

        assertNotNull(authUser);
        assertEquals(authUser.getLogin(), "TestUser");
        dao.deleteAuthUser(id);
    }

    @Test
    void deleteAuthUser(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getById(id);
        dao.deleteAuthUser(id);
        AuthUser authDelete = dao.getById(id);
        assertNull(authDelete);
    }

    @Test
    void blockUser() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        dao.blockUser(Role.BLOCKED, id);
        AuthUser authUser = dao.getById(id);
        assertEquals(authUser.getRole(), Role.BLOCKED);
        dao.deleteAuthUser(id);
    }

}