package com.github.DonBirnam.library.service.impl;



import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private Role role;

    private UserDao userDao = DefaultUserDao.getInstance();

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public void deleteUser(String login) {
        userDao.deleteUser(login);

    }

    @Override
    public boolean isExist(String login, String password) {
        User user = userDao.showUser(login);
        if ((user != null) && user.getPassword().equals(password)) {
            role = setRole(user.getRole());
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Role setRole(String role) {
        Role type = Role.GUEST;
        switch (role){
            case "user":type=Role.USER;
                break;
            case "librarian":type=Role.LIBRARIAN;
                break;
        }

        return type;
    }

    @Override
    public User getByLogin(String login) {
        User user = userDao.showUser(login);
        if (user == null) {
            return null;
        }
        else {
            return user;
    }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDao.changePersonalData(user);
    }
}



