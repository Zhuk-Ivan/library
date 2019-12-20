package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;
import com.github.DonBirnam.library.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {

    Long createOrder(Order order);

    OrderFin findById(Long id);

    void deleteOrder(Long id);

    List<OrderFin> getAllOrders();

    void filledOrder(OrderStatus orderStatus, Long id);

    void approveOrder(LocalDateTime takeDate, LocalDateTime expireDate, Long id);

    List<OrderFin> getOrderByUserId(Long userId);


}
