package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultUserDaoTest {
    private UserDao userDao= DefaultUserDao.getInstance();

    public User testUser(){
        User user=new User(1L,"Лера","Покрышкина","+375296571894","Lera@samsabaka.com","Lera","132","user");
        return user;
    }

    @Test
    public void saveUser() {
        User user = testUser();
        userDao.saveUser(user);
        User testUser=userDao.showUser(user.getLogin());
        assertEquals(user.getFirstName(),testUser.getFirstName());
        assertEquals(user.getPassword(),testUser.getPassword());
        userDao.deleteUser(testUser.getLogin());
    }


    @Test
    void getUserAdmin() {
        assertEquals("DonBirnam", DefaultUserDao.getInstance().showUser("DonBirnam").getLogin());
        assertEquals("12345", DefaultUserDao.getInstance().showUser("DonBirnam").getPassword());
        assertEquals("librarian", DefaultUserDao.getInstance().showUser("DonBirnam").getRole());
        assertNotNull(DefaultUserDao.getInstance().showUser("DonBirnam"));
    }

    @Test
    void getAllUsersList(){
        assertNotNull(DefaultUserDao.getInstance().getAllUsers());
    }
}
