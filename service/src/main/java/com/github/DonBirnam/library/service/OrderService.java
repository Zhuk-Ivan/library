package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface OrderService {

    void save(Order order, HttpSession session);

    OrderFin find(Long id);

    void delete(Long id);

    List<OrderFin> getAllOrders();

    void approve(LocalDateTime takeDate, LocalDateTime expireDate, Long id);

    List<OrderFin> getOrdersByUserId(Long userId);

    void addTempOrder(BookFull bookFull, HttpSession session);

    Set<BookFull> getTempOrders(HttpSession session);

    boolean canMakeTempOrder(HttpSession session);

    void removeTempOrders(HttpSession session);

    void removeBookFromTempOrders(HttpSession session, BookFull bookFull);



}
