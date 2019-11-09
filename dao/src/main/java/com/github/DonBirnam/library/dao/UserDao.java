package com.github.DonBirnam.library.dao;


import com.github.DonBirnam.library.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void updateUser(User user);

    User getById(Long id);

    void deleteUser(Long id);

    List<User> getAllUsers();


}