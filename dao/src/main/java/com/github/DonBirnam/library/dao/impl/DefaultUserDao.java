package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.dao.converter.UserConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.entity.UserEntity;
import com.github.DonBirnam.library.dao.repository.AuthUserRepository;
import com.github.DonBirnam.library.dao.repository.UserRepository;
import com.github.DonBirnam.library.model.User.BlockedUser;
import com.github.DonBirnam.library.model.User.User;
import com.github.DonBirnam.library.model.User.UserFull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultUserDao implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public DefaultUserDao(UserRepository userRepository, AuthUserRepository authUserRepository) {
        this.userRepository = userRepository;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public Long saveUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final AuthUserEntity authUserEntity = authUserRepository.getOne(user.getAuthUserId());
        userEntity.setAuthUserEntity(authUserEntity);
        userRepository.save(userEntity);
        return userEntity.getId();

    }

    @Override
    public void updateUser(User user, Long id) {
        userRepository.update(id, user.getFirstName(), user.getLastName(), user.getPhone());

    }

    @Override
    public UserFull getById(Long id) {
        final UserEntity userEntity = userRepository.getOne(id);
        return UserConverter.fromEntity(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserFull> getAllNonBlockedUsers() {
       List<UserEntity> users = userRepository.findNonBlockedUsers();
       return UserConverter.fromEntityList(users);
    }

    @Override
    public List<BlockedUser> getAllBlockedUsers() {
        List<UserEntity> users = userRepository.findBlockedUsers();
        return UserConverter.fromBlockedEntityList(users);
    }
}


