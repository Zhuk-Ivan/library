package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OrderConverter {
    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }


        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setCreateDate(order.getCreateDate());
        orderEntity.setTakeDate(order.getTakeDate());
        orderEntity.setExpireDate(order.getExpireDate());
        orderEntity.setOrderStatus(order.getOrderStatus());

        return orderEntity;
    }

    public static OrderFin fromEntity(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        return new OrderFin(
                orderEntity.getId(),
                orderEntity.getAuthUserEntity().getLogin(),
                orderEntity.getCreateDate(),
                orderEntity.getTakeDate(),
                orderEntity.getExpireDate(),
                orderEntity.getBooks().stream().map(BookConverter::fromEntity).collect(Collectors.toSet()));
    }

    public static List<OrderFin> fromEntityList(List<OrderEntity> orderEntities) {
        if (orderEntities == null){
            return null;
        }
        final List<OrderFin> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(fromEntity(orderEntity));
        }
        return orders;
    }
}
