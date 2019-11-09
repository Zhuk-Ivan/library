//package com.github.DonBirnam.dao;
//
//import com.github.DonBirnam.library.dao.AuthUserDao;
//import com.github.DonBirnam.library.dao.UserDao;
//import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
//import com.github.DonBirnam.library.dao.entity.UserEntity;
//import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;
//import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
//import com.github.DonBirnam.library.model.AuthUser;
//import com.github.DonBirnam.library.model.Role;
//import com.github.DonBirnam.library.model.User;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class DefaultAuthUserTest {
//    private AuthUserDao dao = DefaultAuthUserDao.getInstance();
//    private UserDao userDao= DefaultUserDao.getInstance();
//
//    @Test
//    void saveAuthUser() {
//
//
//
//        AuthUserEntity authUserEntity = new AuthUserEntity(null, "q", "p", Role.USER, null);
//        UserEntity userEntity = new UserEntity(null,"user","Ivan","Zhuk","+375336559237",authUserEntity);
//        authUserEntity.setUserEntity(userEntity);
//
//
//        boolean saved =  dao.saveAuthUser(authUser);
//        assertNotNull(user);
//
//    }
//
//}
