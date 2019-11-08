package com.github.DonBirnam.library.test;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
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

    private UserDTO testUser() {
        UserDTO user=new UserDTO();
        user.setId(1L);
        user.setFirstName("Лера");
        user.setLastName("Покрышкина");
        user.setPhone("+375296571894");
        user.setEmail("Lera@samsabaka.com");
        user.setLogin("Lera");
        user.setRole(Role.USER);
        return user;
    }

    @Test
    void getAllUsers() {
        when(dao.getAllUsers()).thenReturn(new ArrayList<>());
        List<UserDTO> users = service.getAllUsers();
        assertNotNull(users);
    }

    @Test
    void userIsExistTest() {
        Assertions.assertFalse(service.getInstance().isExist("Ivan"));
    }


    @Test
    void testLoginNotExist() {
        when(dao.showUser("DonBirnam")).thenReturn(null);
        UserDTO user = service.getByLogin("DonBirnam");
        assertNull(user);
    }

    @Test
    public void getByLogin() {
        UserDTO user= testUser();
        when(service.getByLogin(user.getLogin())).thenReturn(user);
        assertNotNull(service.getByLogin(user.getLogin()));
        assertEquals("Lera",service.getByLogin(user.getLogin()).getLogin());
    }


    @Test
    public void deleteUser() {
        UserDTO user= testUser();
        when(service.getByLogin(user.getLogin())).thenReturn(user);
        service.deleteUser(user.getId());
        when(service.getByLogin(user.getLogin())).thenReturn(null);
        assertNull(service.getByLogin(user.getLogin()));
    }


    @Test
    void testLoginCorrect() {
        when(dao.showUser("Lera")).thenReturn(testUser());
        UserDTO user = service.getByLogin("Lera");
        assertNotNull(user);
        assertEquals(user.getLogin(), "Lera");
        assertNotNull(user.getFirstName(), "Лера");
    }

    @Test
    void testChangeRole() {
        when(dao.showUser("Lera")).thenReturn(testUser());
        UserDTO user = service.getByLogin("Lera");
        Long id = user.getId();
        service.block(Role.USER, id);

        assertEquals( user.getRole(), Role.USER);
    }


}


