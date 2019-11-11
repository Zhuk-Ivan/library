package com.github.DonBirnam.library.service;


import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;

import java.util.List;

public interface UserService {

        Long save(User user);

        UserFull getUserById(Long id);

        void updateUser(User user);

        void delete(Long id);

        List<UserFull> getAllUsers();
}
