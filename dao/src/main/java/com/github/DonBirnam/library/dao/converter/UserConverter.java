package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;

public class UserConverter {
    public static UserEntity toEntity(UserRegDTO userRegDTO) {
        if (userRegDTO == null) {
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(userRegDTO.getId());
        userEntity.setFirstName(userRegDTO.getFirstName());
        userEntity.setLastName(userRegDTO.getLastName());
        userEntity.setPhone(userRegDTO.getPhone());
        userEntity.setEmail(userRegDTO.getEmail());
        return userEntity;
    }

    public static UserDTO fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getAuthUserEntity().getLogin(),
                userEntity.getAuthUserEntity().getRole());

    }

}

