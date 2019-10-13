package com.github.DonBirnam.library.dao;


import com.github.DonBirnam.library.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void deleteUser(User user);

    User showUser(String login);

    List <User> getAllUsers();

}