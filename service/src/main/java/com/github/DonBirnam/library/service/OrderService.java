package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.OrderSaveDTO;

import java.util.List;

public interface OrderService {

    void save(OrderSaveDTO order);

    OrderSaveDTO find(Long id);

    void update(OrderSaveDTO order);

    void delete(Long id);

    List<OrderSaveDTO> getAllOrders();

    List<OrderSaveDTO> getAllUsersOrders();

    List<OrderSaveDTO> getOrdersByUserId(Long userId);

}
