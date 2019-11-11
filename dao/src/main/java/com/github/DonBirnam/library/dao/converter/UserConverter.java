package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;


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

        return userEntity;
    }

    public static UserFull fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserFull(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getAuthUserEntity().getLogin(),
                userEntity.getAuthUserEntity().getPassword(),
                userEntity.getAuthUserEntity().getRole());
    }

}

