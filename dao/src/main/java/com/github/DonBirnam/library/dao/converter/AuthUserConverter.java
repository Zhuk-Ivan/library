package com.github.DonBirnam.library.dao.converter;


import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.model.UserLoginDTO;
import com.github.DonBirnam.library.model.UserRegDTO;

public class AuthUserConverter {
    public static AuthUserEntity toEntity(UserRegDTO userRegDTO) {
        if (userRegDTO == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(userRegDTO.getId());
        authUserEntity.setLogin(userRegDTO.getLogin());
        authUserEntity.setPassword(userRegDTO.getPassword());
        authUserEntity.setRole(userRegDTO.getRole());

        return authUserEntity;
    }


    public static UserLoginDTO fromEntity(AuthUserEntity authUserEntity) {
        if (authUserEntity == null) {
            return null;
        }
        return new UserLoginDTO(
                authUserEntity.getId(),
                authUserEntity.getLogin(),
                authUserEntity.getPassword(),
                authUserEntity.getRole());
    }
}
