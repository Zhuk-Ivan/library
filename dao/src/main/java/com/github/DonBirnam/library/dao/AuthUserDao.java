package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.Role;

public interface AuthUserDao {

    Long saveAuthUser(AuthUser authUser);

    void changeAuthUserPass(String password, Long id);

    AuthUser getById(Long id);

    AuthUser getByLogin(String login);

    void deleteAuthUser(Long id);

    void blockUser(Role role, Long id);

}
