package com.github.DonBirnam.library.dao.converter;


import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.model.User.AuthUser;

public class AuthUserConverter {
    public static AuthUserEntity toEntity(AuthUser authUser) {
        if (authUser == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(authUser.getId());
        authUserEntity.setLogin(authUser.getLogin());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setRole(authUser.getRole());

        return authUserEntity;
    }


    public static AuthUser fromEntity(AuthUserEntity authUserEntity) {
        if (authUserEntity == null) {
            return null;
        }
        return new AuthUser(
                authUserEntity.getId(),
                authUserEntity.getLogin(),
                authUserEntity.getPassword(),
                authUserEntity.getRole());
    }
}
