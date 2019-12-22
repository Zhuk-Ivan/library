package com.github.DonBirnam.dao;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.dao.config.DaoConfig;
import com.github.DonBirnam.library.model.*;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultOrderTest {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthUserDao authUserDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AuthorDao authorDao;


    private AuthUser testUser() {
        AuthUser user = new AuthUser(null, "TestUser",  "Pes", Role.USER);
        Long id = authUserDao.saveAuthUser(user);
        AuthUser authUser = authUserDao.getById(id);
        return authUser;
    }
    OrderStatus orderStatus = OrderStatus.CREATED;

    String str = "1986-04-08 12:30";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime createDate = LocalDateTime.parse(str, formatter);
    LocalDateTime takeDate = LocalDateTime.now();
    LocalDateTime expireDate = takeDate.plusDays(7);

    @Test
    void saveOrder() {
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Set<Long> booksId = new HashSet<>();
        booksId.add(bookId);
        Order order = new Order(null, booksId, testUser().getId() , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        OrderFin testOrder = orderDao.findById(id);

        assertEquals(testOrder.getLogin(), testUser().getLogin());
    }

    @Test
    public void findById(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Set<Long> booksId = new HashSet<>();
        booksId.add(bookId);
        Order order = new Order(null, booksId, testUser().getId() , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        OrderFin testOrder = orderDao.findById(id);

        assertNotNull(testOrder);
    }

    @Test
    public void getOrders(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Set<Long> booksId = new HashSet<>();
        booksId.add(bookId);
        Order order = new Order(null, booksId, testUser().getId() , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        List<OrderFin> orders = orderDao.getAllOrders();

        assertNotNull(orders);
    }

    @Test
    public void getOrderByUserId(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Set<Long> booksId = new HashSet<>();
        booksId.add(bookId);
        Order order = new Order(null, booksId, testUser().getId() , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        AuthUser authUser = authUserDao.getByLogin("TestUser");
        List<OrderFin> orders = orderDao.getOrderByUserId(authUser.getId());
        String login = orders.iterator().next().getLogin();

        assertNotNull(orders);
        assertEquals(login, testUser().getLogin());
    }

    @Test
    public void updateOrder(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Set<Long> booksId = new HashSet<>();
        booksId.add(bookId);
        Order order = new Order(null, booksId, testUser().getId() , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        orderDao.approveOrder(takeDate, expireDate, id);


        OrderFin updatedOrder = orderDao.findById(id);
        assertNotNull(updatedOrder.getExpireDate());
    }

    @Test
    public void countBooksInOrder(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
        Long anotherBookId = bookDao.createBook(new Book(null, "Пролетая над гнездом кукушки", 360, "978-5911810808", Genre.DRAMA, BookStatus.FREE, 3, authorId));
        Set<Long> booksId = new HashSet<>(Arrays.asList(bookId, anotherBookId));
        Long userId = authUserDao.saveAuthUser(new AuthUser(null, "User",  "Pes", Role.USER));
        Order order = new Order(null, booksId, userId , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        int booksCount = authUserDao.countBooksInOrders(userId);

        assertEquals(booksCount, 2);
    }

    @Test
    public void selectNearestDate(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long bookId = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 1, authorId));
        Long anotherBookId = bookDao.createBook(new Book(null, "Пролетая над гнездом кукушки", 360, "978-5911810808", Genre.DRAMA, BookStatus.FREE, 3, authorId));
        Set<Long> booksId = new HashSet<>(Arrays.asList(bookId, anotherBookId));
        Long userId = authUserDao.saveAuthUser(new AuthUser(null, "User",  "Pes", Role.USER));
        Order order = new Order(null, booksId, userId , createDate, null, null, orderStatus);
        Long id = orderDao.createOrder(order);

        orderDao.approveOrder(takeDate, expireDate, id);


        LocalDateTime nearestDate = bookDao.nearestDateToReturn(bookId);

        assertEquals(nearestDate, expireDate);
    }



}

