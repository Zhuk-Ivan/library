//package com.github.DonBirnam.dao;
//
//import com.github.DonBirnam.library.dao.HibernateUtil;
//import com.github.DonBirnam.library.dao.OrderDao;
//import com.github.DonBirnam.library.dao.converter.AuthUserConverter;
//import com.github.DonBirnam.library.dao.converter.BookConverter;
//import com.github.DonBirnam.library.dao.converter.OrderConverter;
//import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
//import com.github.DonBirnam.library.dao.entity.BookEntity;
//import com.github.DonBirnam.library.dao.entity.OrderEntity;
//import com.github.DonBirnam.library.dao.impl.DefaultOrderDao;
//import com.github.DonBirnam.library.model.*;
//import com.github.DonBirnam.library.model.User.AuthUser;
//import org.hibernate.Session;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class DefaultOrderTest {
//    private OrderDao orderDao = DefaultOrderDao.getInstance();
//    private Author author = new Author(100L,"Test","Author");
//    private Book book = new Book(100L, "TestBook", author,480,"978-5170946105", Genre.DRAMA, BookStatus.FREE,3);
//    private AuthUser authUser = new AuthUser(100L, "TestUser","11111", Role.USER);
//    String str = "1986-04-08 12:30";
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    LocalDateTime createDate = LocalDateTime.parse(str, formatter);
//    private Order order = new Order(100L, book, authUser, createDate, null, null);
//
//
//    @Test
//    void saveOrder() {
//        OrderEntity orderEntity = OrderConverter.toEntity(order);
//        BookEntity bookEntity = BookConverter.toEntity(book);
//        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//        orderEntity.setAuthUserEntity(authUserEntity);
//        orderEntity.getBooks().add(bookEntity);
//        authUserEntity.getOrders().add(orderEntity);
//        bookEntity.getOrders().add(orderEntity);
//        session.save(orderEntity);
//        session.saveOrUpdate(authUserEntity);
//        session.saveOrUpdate(bookEntity);
//        session.getTransaction().commit();
//
//
//    }
//}

