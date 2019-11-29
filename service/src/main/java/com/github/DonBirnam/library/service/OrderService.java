package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface OrderService {

    void save(Order order);

//    Order find(Long id);
//
//    void delete(Long id);

    List<OrderFin> getAllOrders();

    void approve(LocalDateTime takeDate, LocalDateTime expireDate, Long id);
//
//    List<Order> getAllUsersOrders();
//
    List<OrderFin> getOrdersByUserId(Long userId);

    void addTempOrder(BookFull bookFull);

    Set<BookFull> getTempOrders();

    void removeTempOrders();

}
