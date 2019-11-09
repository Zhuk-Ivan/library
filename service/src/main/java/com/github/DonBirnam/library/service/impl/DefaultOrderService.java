//package com.github.DonBirnam.library.service.impl;
//
//import com.github.DonBirnam.library.dao.OrderDao;
//import com.github.DonBirnam.library.dao.impl.DefaultOrderDao;
//import com.github.DonBirnam.library.model.Order;
//import com.github.DonBirnam.library.service.OrderService;
//
//import java.util.List;
//
//public class DefaultOrderService implements OrderService {
//
//    private OrderDao orderDao = DefaultOrderDao.getInstance();
//
//    private static class SingletonHolder {
//        static final OrderService HOLDER_INSTANCE = new DefaultOrderService();
//    }
//
//    public static OrderService getInstance(){
//        return DefaultOrderService.SingletonHolder.HOLDER_INSTANCE;
//    }
//
//    @Override
//    public void save(Order order) {
//        orderDao.createOrder(order);
//    }
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
//    @Override
//    public List<Order> getAllOrders() {
//        return orderDao.getAllOrders();
//    }
//
//    @Override
//    public List<Order> getAllUsersOrders() {
//        return orderDao.getAllUsersOrders();
//    }
//
//    @Override
//    public List<Order> getOrdersByUserId(Long userId) {
//        return orderDao.getOrderByUserId(userId);
//    }
//}
