package com.github.DonBirnam.library.dao.repository;

import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.model.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update AuthUserEntity set password = :password where id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);

    AuthUserEntity findByLogin(String login);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update AuthUserEntity set role = :role where id = :id")
    void updateRole(@Param("role") Role role, @Param("id") Long id);

}
