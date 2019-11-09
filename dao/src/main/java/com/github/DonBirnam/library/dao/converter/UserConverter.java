package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.User;


public class UserConverter {


    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setEmail(user.getEmail());
        userEntity.setAuthUserEntity(AuthUserConverter.toEntity(user.getAuthUser()));

        return userEntity;
    }

    public static User fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                AuthUserConverter.fromEntity(userEntity.getAuthUserEntity()));
    }

}

