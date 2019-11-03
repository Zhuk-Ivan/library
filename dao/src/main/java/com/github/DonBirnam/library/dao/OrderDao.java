package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Order;

import java.util.List;

public interface OrderDao {

    void createOrder(Order order);

    Order findById(Long id);

    void updateOrder(Order order);

    void deleteOrder(Long id);

    List<Order> getAllOrders();

    List<Order> getOrderByUserId(Long userId);
}
