package com.github.DonBirnam.library.service.impl;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private UserDao userDao = DefaultUserDao.getInstance();

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public Long save(User user) {
        Long userId = userDao.saveUser(user);
        return userId;

    }

    @Override
    public UserFull getUserById(Long id) {
        return userDao.getById(id);

    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteUser(id);

    }

    public List<UserFull> getAllUsers(){
       return userDao.getAllUsers();
    }
}



