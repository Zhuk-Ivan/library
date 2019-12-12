package com.github.DonBirnam.library.service.impl;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserDao userDao;

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Long save(User user) {
        Long userId = userDao.saveUser(user);
        return userId;

    }

    @Override
    @Transactional
    public UserFull getUserById(Long id) {
        return userDao.getById(id);

    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        userDao.updateUser(user, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.deleteUser(id);

    }

    @Override
    @Transactional
    public List<UserFull> getAllUsers(){
       return userDao.getAllUsers();
    }
}



