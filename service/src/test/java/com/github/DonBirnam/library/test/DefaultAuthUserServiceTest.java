package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultAuthUserServiceTest {

    @Mock
    AuthUserDao dao;

    @InjectMocks
    DefaultAuthUserService service;
    private AuthUser authUser = new AuthUser(1L, "TestUser", "56789", Role.USER);
    private AuthUser differentAuthUser = new AuthUser(2L, "User", "11111", Role.LIBRARIAN);



    @Test
    void isExist( ){
        when(dao.getByLogin("TestUser")).thenReturn(authUser);

        boolean Exist = service.isExist("TestUser");

        assertEquals(true, Exist);
    }
    @Test
    public void getById() {
        when(dao.getById(2L)).thenReturn(differentAuthUser);

        assertNotNull(service.getById(2l));
    }


    @Test
    public void getByLogin() {
        when(dao.getByLogin("TestUser")).thenReturn(authUser);
        AuthUser user = service.getByLogin("TestUser");
        assertEquals(authUser, user);
        when(dao.getByLogin("WrongUser")).thenReturn(null);
        AuthUser wrongUser = service.getByLogin("WrongUser");
        assertNull(wrongUser);
    }


}

