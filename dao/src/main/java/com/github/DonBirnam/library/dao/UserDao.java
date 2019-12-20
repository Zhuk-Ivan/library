package com.github.DonBirnam.library.dao;


import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;

import java.util.List;

public interface UserDao {

    Long saveUser(User user);

    void updateUser(User user, Long id);

    UserFull getById(Long id);

    void deleteUser(Long id);

//    List<UserFull> getAllUsers();

    List<UserFull> getAllNonBlockedUsers();


}