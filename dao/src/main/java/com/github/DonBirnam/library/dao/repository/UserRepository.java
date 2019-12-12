package com.github.DonBirnam.library.dao.repository;

import com.github.DonBirnam.library.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity set firstName=:firstName, lastName=:lastName, phone = :phone" +
            " where id=:id")
    void update(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
                @Param("phone") String phone);
}
