package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.config.DaoConfig;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultUserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthUserDao authUserDao;

    @Test
    void saveUser() {
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long userId = userDao.saveUser(user);


        UserFull test = userDao.getById(userId);

        assertNotNull(test);
        assertEquals(test.getFirstName(), user.getFirstName());
        assertEquals(test.getEmail(), user.getEmail());

        userDao.deleteUser(userId);
    }

    @Test
    void getById(){
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long userId = userDao.saveUser(user);

        UserFull userBD = userDao.getById(userId);

        assertEquals(userBD.getEmail(), "Pes@samsobaka");
        assertEquals(userBD.getFirstName(), "Test");

        userDao.deleteUser(userId);
    }

    @Test
    void getUsers() {
        List<UserFull> users = userDao.getAllUsers();

        assertNotNull(users);
    }


    @Test
    public void updateBook(){
        Long id = authUserDao.saveAuthUser(new AuthUser(null, "TestUser", "56789", Role.USER));
        User user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka", id);
        Long userId = userDao.saveUser(user);

        User newUser = new User(null, "NewUser", "Pes","+6578895642", "Pes@samsobaka", id);
        userDao.updateUser(newUser, userId);

        UserFull test = userDao.getById(userId);
        assertEquals(test.getFirstName(), "NewUser");
        assertEquals(test.getLastName(), "Pes");
    }
}


