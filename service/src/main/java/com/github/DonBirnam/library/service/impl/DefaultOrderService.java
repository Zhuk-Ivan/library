package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;
import com.github.DonBirnam.library.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderDao orderDao;

    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    Set<BookFull> tempOrders = new HashSet<>();

    @Override
    @Transactional
    public void save(Order order) {
        orderDao.createOrder(order);
        removeTempOrders();
    }
//
//    @Override
//    public Order find(Long id) {
//       return orderDao.findById(id);
//    }
//
//    @Override
//    public void update(Order order) {
//        orderDao.updateOrder(order);
//    }
//
//    @Override
//    public void delete(Long id) {
//        orderDao.deleteOrder(id);
//    }
//
    @Override
    @Transactional
    public List<OrderFin> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    @Transactional
    public void approve(LocalDateTime takeDate, LocalDateTime expireDate, Long id) {
        orderDao.approveOrder(takeDate,expireDate,id);
    }
//
//    @Override
//    public List<Order> getAllUsersOrders() {
//        return orderDao.getAllUsersOrders();
//    }

    @Override
    @Transactional
    public List<OrderFin> getOrdersByUserId(Long userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public void addTempOrder(BookFull bookFull) {
        tempOrders.add(bookFull);
    }

    @Override
    public Set<BookFull> getTempOrders() {
         return tempOrders;

    }

    @Override
    public boolean canMakeTempOrder() {
        if (tempOrders.size() < 3) {
            return true;
        }
        else return false;
    }

    @Override
    public void removeTempOrders() {
        tempOrders.clear();
    }
}
