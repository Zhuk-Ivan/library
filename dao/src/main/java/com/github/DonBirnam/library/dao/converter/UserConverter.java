package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.User.BlockedUser;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

    public static BlockedUser blockedUserFromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Map<LocalDateTime, Set<BookFull>> booksToReturn = new HashMap<>();
        if (userEntity.getAuthUserEntity().getOrders() != null) {
            for (OrderEntity order: userEntity.getAuthUserEntity().getOrders()){
                if (order.getExpireDate()!= null){
                    booksToReturn.put(order.getExpireDate(), order.getBooks().stream().map(BookConverter::fromEntity).collect(Collectors.toSet()));
                }
            }
        }
        return new BlockedUser(
                userEntity.getId(),
                userEntity.getAuthUserEntity().getLogin(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getAuthUserEntity().getRole(),
                booksToReturn);
    }

    public static List<UserFull> fromEntityList(List<UserEntity> userEntities) {
        if (userEntities == null){
            return null;
        }
        final List<UserFull> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(fromEntity(userEntity));
        }
        return users;
    }

    public static List<BlockedUser> fromBlockedEntityList(List<UserEntity> userEntities) {
        if (userEntities == null){
            return null;
        }
        final List<BlockedUser> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(blockedUserFromEntity(userEntity));
        }
        return users;
    }

}

