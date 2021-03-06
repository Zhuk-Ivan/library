package com.github.DonBirnam.library.dao.repository;

import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update BookEntity set status = :status where id = :id")
    void updateBookStatus(@Param("status") BookStatus status, @Param("id") Long id);

    List<BookEntity> findBookEntitiesByGenre(Genre genre);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update BookEntity set title=:title, pageCount=:pageCount, isbn = :isbn," +
            " genre = :genre, inStock = :inStock where id=:id")
    void update(@Param("id") Long id, @Param("title") String title, @Param("pageCount") int pageCount,
                @Param("isbn") String isbn, @Param("genre") Genre genre, @Param("inStock") int inStock);


    @Transactional
    @Query("select case when ord is not null and ord.orderStatus='ISSUED' then min(ord.expireDate) end from BookEntity as be join be.orders ord where be.id = :id")
    LocalDateTime findNearestDate(@Param("id") Long id);

    @Transactional
    @Query("from BookEntity be where be.authorEntity.id = :id")
    List<BookEntity> findByAuthorId(@Param("id") Long id);
}


