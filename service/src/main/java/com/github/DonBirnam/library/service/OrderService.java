package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Order;

import java.util.List;

public interface OrderService {

    void save(Order order);

    Order find(Long id);

    void update(Order order);

    void delete(Long id);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long userId);

}
