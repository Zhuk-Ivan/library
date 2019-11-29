package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultUserDaoTest {
    private UserDao userDao = DefaultUserDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();


    @Test
    void saveUser() {
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long UserId = userDao.saveUser(user);


        final UserEntity userEntity = HibernateUtil.getSession().get(UserEntity.class, UserId);

        assertNotNull(userEntity);
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getEmail(), user.getEmail());

        userDao.deleteUser(UserId);
        authUserDao.deleteAuthUser(id);
    }

    @Test
    void getById(){
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long UserId = userDao.saveUser(user);

        UserFull userBD = userDao.getById(UserId);

        assertEquals(userBD.getEmail(), "Pes@samsobaka");
        assertEquals(userBD.getFirstName(), "Test");

        userDao.deleteUser(UserId);
        authUserDao.deleteAuthUser(id);
    }

    @Test
    void getUsers() {
        List<UserFull> users = userDao.getAllUsers();

        assertNotNull(users);
    }


    @Test
    void deleteUser() {
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long UserId = userDao.saveUser(user);

        assertNotNull(userDao.getById(UserId));
        userDao.deleteUser(UserId);

        assertNull(userDao.getById(id));
        authUserDao.deleteAuthUser(id);
    }
}


