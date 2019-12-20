package com.github.DonBirnam.library.test;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {

    @Mock
    UserDao dao;

    @InjectMocks
    DefaultUserService service;


    private User user = new User(100L , "Test", "User","+375291111111", "Pes@samsobaka",100L);
    private UserFull userFull = new UserFull(100L , "Test", "User","+375291111111", "Pes@samsobaka", "TestUser", "11111", Role.USER);

    @Test
    void saveUser(){
       when(dao.saveUser(user)).thenReturn(100L);

       Long id = service.save(user);

       assertEquals(100L, id);

    }

//    @Test
//    void getAllUsers() {
//        when(dao.getAllUsers()).thenReturn(new ArrayList<>());
//        List<UserFull> users = service.getAllUsers();
//        assertNotNull(users);
//    }


    @Test
    public void getById() {
        when(dao.getById(100L)).thenReturn(userFull);

        assertNotNull(service.getUserById(100L));
        assertEquals("TestUser",service.getUserById(100L).getLogin());
    }

    @Test
    void update() {
        doNothing().when(dao).updateUser(any(), any());

        service.updateUser(user, 100L);

        verify(dao, times(1)).updateUser(user, 100L);
    }

    @Test
    void delete(){
        doNothing().when(dao).deleteUser(anyLong());

        service.delete(100L);

        verify(dao, times(1)).deleteUser(100L);
    }

}

