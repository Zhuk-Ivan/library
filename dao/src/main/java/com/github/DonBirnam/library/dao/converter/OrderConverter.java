//package com.github.DonBirnam.library.dao.converter;
//
//import com.github.DonBirnam.library.dao.entity.OrderEntity;
//import com.github.DonBirnam.library.dao.impl.DefaultBookDao;
//import com.github.DonBirnam.library.dao.impl.DefaultUserDao;
//import com.github.DonBirnam.library.model.Book;
//import com.github.DonBirnam.library.model.OrderDTO;
//import com.github.DonBirnam.library.model.OrderSaveDTO;
//import com.github.DonBirnam.library.model.UserDTO;
//
//public class OrderConverter {
//    public static OrderEntity toEntity(OrderSaveDTO order) {
//        if (order == null) {
//            return null;
//        }
//        Book book = DefaultBookDao.getInstance().findById(order.getBookId());
//        UserDTO user = DefaultUserDao.getInstance().getById(order.getUserId());
//
//
//        final OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setId(order.getId());
//        orderEntity.setUserEntity(UserConverter.toEntity(user);
//        orderEntity.setCreateDate(order.getCreateDate());
//        orderEntity.setTakeDate(order.getTakeDate());
//        orderEntity.setExpireDate(order.getExpireDate());
//        return orderEntity;
//    }
//
//    public static OrderDTO fromEntity(OrderEntity orderEntity) {
//        if (orderEntity == null) {
//            return null;
//        }
//
//        return new OrderDTO(
//                orderEntity.getId(),
//
//                orderEntity.getUserEntity().getAuthUserEntity().getLogin(),
//                orderEntity.getCreateDate(),
//                orderEntity.getTakeDate(),
//                orderEntity.getExpireDate());
//
//    }
//}
