package com.github.DonBirnam.library.service;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.User;

    import java.util.List;
    public interface UserService {

        void saveUser(User user);

        void deleteUser(String login);

        boolean isExist(String login, String password);

        User getByLogin(String login);

        List<User> getAllUsers();

        void updateUser(User user);

        void block(Role role, Long id);



}
