package com.github.DonBirnam.library.service;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.User;

import java.io.IOException;

public interface UserService {

    void saveUser(User user);

    boolean isExist(String login, String password);

    Role setRole(String role);

    User getByLogin(String login);

}
