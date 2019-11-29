package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.UserService;

public class DefaultAuthUserService implements AuthUserService {

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private UserService userService = DefaultUserService.getInstance();

    private static class SingletonHolder {
        static final AuthUserService HOLDER_INSTANCE = new DefaultAuthUserService();
    }

    public static AuthUserService getInstance() {
        return DefaultAuthUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Long save(String firstName, String lastName, String email, String phone, String login, String password) {
        Role role = Role.USER;

        AuthUser authUser = new AuthUser(null, login, password, role);
        Long authId = authUserDao.saveAuthUser(authUser);
        User user = new User(null, firstName, lastName, email, phone, authId);
        userService.save(user);
        return authId;
    }

    @Override
    public AuthUser getById(Long id) {
        return authUserDao.getById(id);
    }

    @Override
    public AuthUser getByLogin(String login) {
        return authUserDao.getByLogin(login);
    }


    @Override
    public void changePass(String password, Long id) {
        authUserDao.changeAuthUserPass(password, id);
    }

    @Override
    public void deleteAuthUser(Long id) {
        authUserDao.deleteAuthUser(id);
    }

    @Override
    public boolean isExist(String login) {
        AuthUser user = authUserDao.getByLogin(login);
        if ((user != null) && user.getLogin().equals(login)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void block(Role role, Long id) {
        authUserDao.blockUser(role,id);

    }

    @Override
    public boolean canMakeAnOrder(Long id) {
        return authUserDao.canMakeAnOrder(id);
    }

}
