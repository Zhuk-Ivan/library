package com.github.DonBirnam.dao;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.config.DaoConfig;
import com.github.DonBirnam.library.model.User.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultAuthUserTest {

    @Autowired
    private AuthUserDao dao;

    @Autowired
    private UserDao userDao;

    @Test
    void saveAuthUser() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        AuthUser authUser = dao.getById(id);
        assertNotNull(authUser);
        assertEquals(authUser.getLogin(), "TestUser");
        dao.deleteById(id);
    }

    @Test
    void getByLogin(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getByLogin("TestUser");

        assertNotNull(authUser);
        assertEquals(authUser.getPassword(), "56789");
        dao.deleteById(id);
    }

    @Test
    void getById(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getById(id);

        assertNotNull(authUser);
        assertEquals(authUser.getLogin(), "TestUser");
        dao.deleteById(id);
    }

    @Test
    void deleteAuthUser(){
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        final AuthUser authUser = dao.getById(id);

        dao.deleteById(authUser.getId());

        assertNull(dao.getByLogin("TestUser"));
    }


    @Test
    void blockUser() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));

        dao.blockUser(Role.BLOCKED, id);

        AuthUser authUser = dao.getByLogin("TestUser");
        assertEquals(authUser.getRole(), Role.BLOCKED);
    }

    @Test
    void updatePassword() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));

        dao.changeAuthUserPass("NewPass", id);

        AuthUser authUser = dao.getByLogin("TestUser");

        assertEquals(authUser.getPassword(), "NewPass");
    }

    @Test
    void getBlockedUsers() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.BLOCKED));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long userId = userDao.saveUser(user);

        List<BlockedUser> users = userDao.getAllBlockedUsers();

        assertNotNull(users);
        assertEquals(users.get(0).getPhone(), user.getPhone());
        assertEquals(users.get(0).getRole(), Role.BLOCKED);
    }

    @Test
    void getNonBlockedUsers() {
        Long id = dao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long userId = userDao.saveUser(user);

        UserFull userBD = userDao.getById(userId);

        List<UserFull> users = userDao.getAllNonBlockedUsers();

        assertNotNull(users);
        assertEquals(users.get(0).getLogin(), userBD.getLogin());
        assertEquals(users.get(0).getRole(), userBD.getRole());
    }

}

