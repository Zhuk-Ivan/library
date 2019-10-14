package com.github.DonBirnam.library.test;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.DefaultUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {

    @Mock
    UserDao dao;

    @InjectMocks
    DefaultUserService service;


    @Test
    void getAllUsers() {
        when(dao.getAllUsers()).thenReturn(new ArrayList<User>());
        List<User> users = service.getAllUsers();
        assertNotNull(users);
    }

    @Test
    void userIsExistTest() {
        Assertions.assertFalse(service.getInstance().isExist("Ivan", "12345"));
    }

    @Test
    void testLoginNotExist() {
        when(dao.showUser("DonBirnam")).thenReturn(null);
        User user = service.getByLogin("DonBirnam");
        assertNull(user);
    }

    @Test
    void testLoginCorrect() {
        when(dao.showUser("DonBirnam")).thenReturn(new User("DonBirnam", "Ivan", "Zhuk", "+375336559237", "ivan.zhuk94@gmail.com"));
        User user = service.getByLogin("DonBirnam");
        assertNotNull(user);
        assertEquals(user.getLogin(), "DonBirnam");
        assertNotNull(user.getFirstName(), "Ivan");
    }


}
