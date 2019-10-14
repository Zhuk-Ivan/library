package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultUserDaoTest {

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
