//package com.github.DonBirnam.dao;
//
//
//import com.github.DonBirnam.library.dao.AuthUserDao;
//import com.github.DonBirnam.library.dao.HibernateUtil;
//import com.github.DonBirnam.library.dao.UserDao;
//import com.github.DonBirnam.library.dao.entity.UserEntity;
//import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;
//import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
//import com.github.DonBirnam.library.model.User.User;
//import com.github.DonBirnam.library.model.User.UserFull;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static junit.framework.TestCase.assertNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class DefaultUserDaoTest {
//    private UserDao userDao = DefaultUserDao.getInstance();
//    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
//
//    private User testUser() {
//        User user = null;
//        return user = new User(null, "Test", "User","+375291111111", "Pes@samsobaka",1000L);
//    }
//
//    @Test
//    void saveUser() {
//        User user = testUser();
//        userDao.saveUser(user);
//
//        final UserEntity userEntity = HibernateUtil.getSession().get(UserEntity.class, 1000L);
//
//        assertNotNull(userEntity);
//    }
//
//    @Test
//    void getById(){
//        final UserEntity userEntity = HibernateUtil.getSession().get(UserEntity.class, 1000L);
//
//        assertEquals(userEntity.getAuthUserEntity().getLogin(), "TestUser");
//
//        assertEquals(userEntity.getFirstName(), "Test");
//    }
//
//    @Test
//    void getUsers() {
//        List<UserFull> users = userDao.getAllUsers();
//
//        assertNotNull(users);
//    }
//
//
//    @Test
//    void deleteUser() {
//
//        assertNotNull(userDao.getById(1000L));
//
//        userDao.deleteUser(1000L);
//
//        assertNull(userDao.getById(1000L));
//
//        authUserDao.deleteAuthUser(1000L);
//    }
//
//}
//
//
