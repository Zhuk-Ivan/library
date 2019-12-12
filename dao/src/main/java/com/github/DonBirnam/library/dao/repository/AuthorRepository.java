package com.github.DonBirnam.library.dao.repository;

import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update AuthorEntity set firstName=:firstName, lastName=:lastName where id=:id")
    void update(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
