package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultUserDaoTest {

    private UserRegDTO testUser() {
        UserRegDTO user = new UserRegDTO();
        user.setId(null);
        user.setFirstName("Алексей");
        user.setLastName("Ждун");
        user.setPhone("+375336559237");
        user.setEmail("Zhdun15@gmail.com");
        user.setLogin("Zhdun");
        user.setPassword("12345");
        user.setRole(Role.USER);
        return user;
  }

    @Test
    void save() {
        UserRegDTO user = testUser();
        Long id = DefaultUserDao.getInstance().saveUser(user);
        String firstName = DefaultUserDao.getInstance().getById(id).getFirstName();
        assertEquals("Алексей", firstName);
    }

    @Test
    void getUserByLogin() {
        String firstName = DefaultUserDao.getInstance().showUser("Alex").getFirstName();
        UserDTO userNull = DefaultUserDao.getInstance().getById(20L);
        assertEquals("Алексей", firstName);
        assertNull(userNull);
    }


    @Test
    void getUsers() {
        List<UserDTO> users = DefaultUserDao.getInstance().getAllUsers();
        assertNotNull(users);
    }

    @Test
    void deleteUser() {
        UserDTO user = DefaultUserDao.getInstance().showUser("Zhdun");
        assertNotNull(DefaultUserDao.getInstance().showUser(user.getLogin()));
        DefaultUserDao.getInstance().deleteUser(user.getId());
        assertNull(DefaultUserDao.getInstance().showUser(user.getLogin()));
    }

    @AfterAll
    public static void cleanUp() {
        EntityManager em = HibernateUtil.getSession();
        if (em != null) {
            em.close();
        }
    }
}

