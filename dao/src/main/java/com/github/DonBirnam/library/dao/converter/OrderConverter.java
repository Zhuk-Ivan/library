package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;


public class OrderConverter {
    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }
//        Set<BookEntity> bookEntities = new HashSet<>();
//        bookEntities.add(BookConverter.toEntity(order.getBook()));
//        for (Book book: books) {
//            bookEntities.add(BookConverter.toEntity(book));
//
//        }

        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
//        orderEntity.setAuthUserEntity(AuthUserConverter.toEntity(order.getAuthUser()));
//        orderEntity.setBooks(bookEntities);
        orderEntity.setCreateDate(order.getCreateDate());
        orderEntity.setTakeDate(order.getTakeDate());
        orderEntity.setExpireDate(order.getExpireDate());

        return orderEntity;
    }

    public static OrderFin fromEntity(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        return new OrderFin(
                orderEntity.getId(),
                orderEntity.getAuthUserEntity().getLogin(),
                orderEntity.getBooks().iterator().next().getTitle(),
                orderEntity.getBooks().iterator().next().getAuthorEntity().getFirstName(),
                orderEntity.getBooks().iterator().next().getAuthorEntity().getLastName(),
                orderEntity.getCreateDate(),
                orderEntity.getTakeDate(),
                orderEntity.getExpireDate());
    }
}
