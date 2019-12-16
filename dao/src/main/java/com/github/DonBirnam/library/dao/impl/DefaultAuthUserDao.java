package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.converter.AuthUserConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.dao.repository.AuthUserRepository;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Set;

@Repository
public class DefaultAuthUserDao implements AuthUserDao{

    private final AuthUserRepository authUserRepository;

    public DefaultAuthUserDao(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Override
    public Long saveAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        authUserRepository.save(authUserEntity);
        return authUserEntity.getId();
    }

    @Override
    public void changeAuthUserPass(String password, Long id) {
        authUserRepository.updatePassword(password, id);
    }

    @Override
    public AuthUser getById(Long id) {
        final AuthUserEntity authUserEntity = authUserRepository.getOne(id);
        return AuthUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public AuthUser getByLogin(String login) {
        final AuthUserEntity authUserEntity = authUserRepository.findByLogin(login);
        return AuthUserConverter.fromEntity(authUserEntity);
    }
    @Override
    public void deleteById(Long id) {
        authUserRepository.deleteById(id);
    }

    @Override
    public void blockUser(Role role, Long id) {
        authUserRepository.updateRole(role, id);
    }

    @Override
    public int countBooksInOrders(Long id) {
        final AuthUserEntity authUserEntity = authUserRepository.getOne(id);
        int bookCount = 0;
        Set<OrderEntity> userOrders = authUserEntity.getOrders();
        Iterator<OrderEntity> iter = userOrders.iterator();
        while (iter.hasNext()){
            bookCount += iter.next().getBooks().size();
        }

        return bookCount;

    }

//    @Override
//    public List<AuthUser> getAllUsers() {
//        Role roleU = Role.USER;
//        Role roleB = Role.BLOCKED;
//        final List<AuthUserEntity> users = HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.role = :roleU or au.role = :roleB")
//                .setParameter("roleU", roleU)
//                .setParameter("roleB", roleB)
//                .list();
//        return users.stream().map(AuthUserConverter::fromEntity)
//                .collect(Collectors.toList());
//
//    }

}
