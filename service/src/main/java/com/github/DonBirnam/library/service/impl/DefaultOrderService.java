//package com.github.DonBirnam.library.service.impl;
//
//import com.github.DonBirnam.library.dao.OrderDao;
//import com.github.DonBirnam.library.dao.impl.DefaultOrderDao;
//import com.github.DonBirnam.library.model.OrderSaveDTO;
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
//    public void save(OrderSaveDTO order) {
//        orderDao.createOrder(order);
//    }
//
//    @Override
//    public OrderSaveDTO find(Long id) {
//       return orderDao.findById(id);
//    }
//
//    @Override
//    public void update(OrderSaveDTO order) {
//        orderDao.updateOrder(order);
//    }
//
//    @Override
//    public void delete(Long id) {
//        orderDao.deleteOrder(id);
//    }
//
//    @Override
//    public List<OrderSaveDTO> getAllOrders() {
//        return orderDao.getAllOrders();
//    }
//
//    @Override
//    public List<OrderSaveDTO> getAllUsersOrders() {
//        return orderDao.getAllUsersOrders();
//    }
//
//    @Override
//    public List<OrderSaveDTO> getOrdersByUserId(Long userId) {
//        return orderDao.getOrderByUserId(userId);
//    }
//}
