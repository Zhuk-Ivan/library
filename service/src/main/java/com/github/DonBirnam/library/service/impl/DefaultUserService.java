package com.github.DonBirnam.library.service.impl;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.User;
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
    public void save(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User getUserById(Long id) {
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

    public List<User> getAllUsers(){
       return userDao.getAllUsers();
    }
}



