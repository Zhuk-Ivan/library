package com.github.DonBirnam.library.dao.repository;

import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderEntity set takeDate = :takeDate, expireDate = :expireDate where id = :id")
    void updateOrderTakeDateAndExpireDate(@Param("takeDate") LocalDateTime takeDate, @Param("expireDate") LocalDateTime expireDate, @Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderEntity set orderStatus = :orderStatus where id = :id")
    void updateOrderStatus(@Param("orderStatus") OrderStatus orderStatus, @Param("id") Long id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("from OrderEntity oe where oe.authUserEntity.id = :id")
    List<OrderEntity> findByUserId(@Param("id") Long id);

}
