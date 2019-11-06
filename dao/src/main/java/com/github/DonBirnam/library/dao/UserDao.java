package com.github.DonBirnam.library.dao;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void deleteUser(String login);

    User showUser(String login);

    List<User> getAllUsers();

    void changePersonalData(User user);

    void blockUser(Role role, Long id);

}