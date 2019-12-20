package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.dao.converter.OrderConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.dao.repository.AuthUserRepository;
import com.github.DonBirnam.library.dao.repository.BookRepository;
import com.github.DonBirnam.library.dao.repository.OrderRepository;
import com.github.DonBirnam.library.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
@Repository
public class DefaultOrderDao implements OrderDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private final OrderRepository orderRepository;
    private final AuthUserRepository authUserRepository;
    private final BookRepository bookRepository;

    public DefaultOrderDao(OrderRepository orderRepository, AuthUserRepository authUserRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.authUserRepository = authUserRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Long createOrder(Order order) {
        OrderEntity orderEntity = OrderConverter.toEntity(order);
        AuthUserEntity authUserEntity = authUserRepository.getOne(order.getAuthUserId());
        orderRepository.save(orderEntity);
        OrderEntity orderEntitySaved = orderRepository.getOne(orderEntity.getId());
        BookEntity bookEntity;
        Iterator<Long> iter = order.getBooks().iterator();
        while (iter.hasNext()){
            bookEntity = bookRepository.getOne(iter.next());
            int inStock = bookEntity.getInStock();
            BookStatus bookStatus = BookStatus.OCCUPIED;
            if (inStock == 1) {
                bookEntity.setInStock(--inStock);
                bookEntity.setStatus(bookStatus);
            }
            else {
                bookEntity.setInStock(--inStock);
            }
            orderEntitySaved.getBooks().add(bookEntity);
            bookEntity.getOrders().add(orderEntitySaved);
            bookRepository.save(bookEntity);
        }
            orderEntitySaved.setAuthUserEntity(authUserEntity);
            authUserEntity.getOrders().add(orderEntitySaved);

            authUserRepository.save(authUserEntity);
            orderRepository.save(orderEntitySaved);
            return orderEntity.getId();
    }

    @Override
    public OrderFin findById(Long id) {
        final OrderEntity orderEntity = orderRepository.getOne(id);
        return OrderConverter.fromEntity(orderEntity);
    }



    @Override
    public void deleteOrder(Long id) {
        OrderEntity orderEntity = orderRepository.getOne(id);
            for (BookEntity bookEntity : orderEntity.getBooks()) {
                int inStock = bookEntity.getInStock();
                BookStatus bookStatus = BookStatus.FREE;
                if (inStock == 0) {
                    bookEntity.setInStock(++inStock);
                    bookEntity.setStatus(bookStatus);
                }
                else {
                    bookEntity.setInStock(++inStock);
                }
                bookEntity.getOrders().remove(orderEntity);
//                orderEntity.getBooks().remove(bookEntity);
                bookRepository.save(bookEntity);
            }
            orderRepository.deleteById(id);
        }


    @Override
    public List<OrderFin> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return OrderConverter.fromEntityList(orders);
    }

    @Override
    public void filledOrder(OrderStatus orderStatus, Long id) {
        orderRepository.updateOrderStatus(orderStatus, id);
    }

    @Override
    public void approveOrder(LocalDateTime takeDate, LocalDateTime expireDate, Long id) {
        OrderStatus orderStatus = OrderStatus.ISSUED;
        orderRepository.updateOrderTakeDateAndExpireDate(takeDate, expireDate, id);
        orderRepository.updateOrderStatus(orderStatus, id);
    }


    @Override
    public List<OrderFin> getOrderByUserId(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        return OrderConverter.fromEntityList(orders);
    }

}



