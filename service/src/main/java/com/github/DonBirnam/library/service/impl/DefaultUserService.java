package com.github.DonBirnam.library.service.impl;


import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;
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
    public UserDTO saveUser(UserRegDTO userRegDTO) {
        userDao.saveUser(userRegDTO);

        UserDTO user = getByLogin(userRegDTO.getLogin());
        if (user == null) {
            long id = userDao.saveUser(userRegDTO);
            return userDao.getById(id);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);

    }

    @Override
    public boolean isExist(String login) {
        UserDTO user = userDao.showUser(login);
        if ((user != null) && user.getLogin().equals(login)) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public UserDTO getByLogin(String login) {
        UserDTO user = userDao.showUser(login);
        if (user == null) {
            return null;
        }
        else {
            return user;
    }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userDao.changePersonalData(userDTO);
    }

    @Override
    public void block(Role role, Long id) {
        userDao.blockUser(role,id);
    }
}



