package com.github.DonBirnam.library.service;


import com.github.DonBirnam.library.model.User;

import java.util.List;

public interface UserService {

        void save(User user);

        User getUserById(Long id);

        void updateUser(User user);

        void delete(Long id);

        List<User> getAllUsers();
}
