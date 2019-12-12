package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultAuthUserService implements AuthUserService {

    private final AuthUserDao authUserDao;
    private final UserDao userDao;

    public DefaultAuthUserService(AuthUserDao authUserDao, UserDao userDao) {
        this.authUserDao = authUserDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Long save(String firstName, String lastName, String email, String phone, String login, String password) {
        Role role = Role.USER;

        AuthUser authUser = new AuthUser(null, login, password, role);
        Long authId = authUserDao.saveAuthUser(authUser);
        User user = new User(null, firstName, lastName, email, phone, authId);
        userDao.saveUser(user);
        return authId;
    }

    @Override
    @Transactional
    public AuthUser getById(Long id) {
        return authUserDao.getById(id);
    }

    @Override
    @Transactional
    public AuthUser getByLogin(String login) {
        return authUserDao.getByLogin(login);
    }


    @Override
    @Transactional
    public void changePass(String password, Long id) {
        authUserDao.changeAuthUserPass(password, id);
    }

    @Override
    @Transactional
    public void deleteAuthUser(Long id) {
        authUserDao.deleteById(id);
    }

    @Override
    @Transactional
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
    @Transactional
    public void block(Role role, Long id) {
        authUserDao.blockUser(role,id);

    }

    @Override
    @Transactional
    public boolean canMakeAnOrder(Long id) {
        return authUserDao.canMakeAnOrder(id);
    }

}
