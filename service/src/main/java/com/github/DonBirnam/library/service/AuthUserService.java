package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.AuthUser;
import com.github.DonBirnam.library.model.Role;

public interface AuthUserService {

    Long save(String firstName, String lastName, String email, String phone, String login, String password);

    AuthUser getById(Long id);

    AuthUser getByLogin(String login);

    void changePass(String password, Long id);

    void deleteAuthUser(Long id);

    boolean isExist(String login);

    void block(Role role, Long id);
}

