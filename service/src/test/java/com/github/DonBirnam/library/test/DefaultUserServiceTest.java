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

    private User testUser() {
        User user=new User();
        user.setId(1L);
        user.setFirstName("Лера");
        user.setLastName("Покрышкина");
        user.setPhone("+375296571894");
        user.setEmail("Lera@samsabaka.com");
        user.setLogin("Lera");
        user.setPassword("123");
        user.setRole("user");
        return user;
    }
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
    public void saveUser() {
        User user= testUser();
        service.saveUser(user);
        when(service.getByLogin(user.getLogin())).thenReturn(user);
        assertEquals("Lera",service.getByLogin(user.getLogin()).getLogin());
    }

    @Test
    void testLoginNotExist() {
        when(dao.showUser("DonBirnam")).thenReturn(null);
        User user = service.getByLogin("DonBirnam");
        assertNull(user);
    }

    @Test
    void testLoginCorrect() {
        when(dao.showUser("Lera")).thenReturn(new User("Lera", "Лера", "Покрышкина", "+375296571894", "ivan.zhuk94@gmail.com"));
        User user = service.getByLogin("Lera");
        assertNotNull(user);
        assertEquals(user.getLogin(), "Lera");
        assertNotNull(user.getFirstName(), "Лера");
    }

    @Test
    public void deleteUser() {
        User user= testUser();
        service.deleteUser(user.getLogin());
        when(service.getByLogin(user.getLogin())).thenReturn(null);
        assertNull(service.getByLogin(user.getLogin()));
    }

    @Test
    public void getByLogin() {
        User user=testUser();
        when(service.getByLogin(user.getLogin())).thenReturn(user);
        assertNotNull(service.getByLogin(user.getLogin()));
        assertEquals("Lera",service.getByLogin(user.getLogin()).getLogin());
    }


}
