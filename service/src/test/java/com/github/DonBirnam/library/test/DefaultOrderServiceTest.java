package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.model.*;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.service.impl.DefaultOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultOrderServiceTest {

    @Mock
    OrderDao dao;

    @Mock
    HttpSession session;

    @InjectMocks
    DefaultOrderService service;

    private BookFull testBook1 = new BookFull(1L, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 5, "Кен", "Кизи");
    private BookFull testBook2 = new BookFull(2L, "Преступление", 360, "978-5567810808", Genre.DETECTIVE, BookStatus.FREE, 4, "Ирвин", "Уэлш");
    private AuthUser user = new AuthUser(1L, "TestUser", "Pes", Role.USER);

    Set<Long> booksId = new HashSet<>(Arrays.asList(1L, 2L));
    Set<BookFull> books = new HashSet<>(Arrays.asList(testBook1, testBook2));
    String str = "1986-04-08 12:30";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime createDate = LocalDateTime.parse(str, formatter);
    LocalDateTime takeDate = LocalDateTime.now();
    LocalDateTime expireDate = takeDate.plusDays(7);

    private Order order = new Order(100L, booksId, user.getId(), createDate, null, null, OrderStatus.CREATED);
    private OrderFin orderDB = new OrderFin(100L, "TestUser", createDate, takeDate, expireDate, books);
    List<OrderFin> userOrders = new ArrayList<>(Arrays.asList(orderDB));


    @Test
    void saveBook() {
        when(dao.createOrder(order)).thenReturn(100L);

        Long id = service.save(order, session);

        assertEquals(id, 100L);

    }

    @Test
    public void getById() {

        when(dao.findById(100L)).thenReturn(orderDB);

        assertNotNull(service.find(100L));
        assertEquals("TestUser", service.find(100L).getLogin());
    }

    @Test
    void getAllOrders() {
        when(dao.getAllOrders()).thenReturn(new ArrayList<>());

        List<OrderFin> orders = service.getAllOrders();

    }

    @Test
    void getOrdersByUserId() {
        when(dao.getOrderByUserId(1L)).thenReturn(userOrders);

        List<OrderFin> orders = service.getOrdersByUserId(1L);

        assertEquals(orders, userOrders);

    }

    @Test
    void approveOrder() {
        doNothing().when(dao).approveOrder(any(), any(), anyLong());

        service.approve(takeDate, expireDate, 100L);

        verify(dao, times(1)).approveOrder(takeDate, expireDate, 100L);

    }

    @Test
    void delete(){
        doNothing().when(dao).deleteOrder(anyLong());

        service.delete(100L);

        verify(dao, times(1)).deleteOrder(100L);
    }


    @Test
    void getTempOrders(){
        when(session.getAttribute("tempBooks")).thenReturn(books);

        Set<BookFull> userTempBooks = service.getTempOrders(session);

        assertEquals(books, userTempBooks);
    }

    @Test
    void addTempOrders(){
        Set<BookFull> tempBooks = new HashSet<>(Arrays.asList(testBook1));
        doNothing().when(session).setAttribute("tempBooks", tempBooks);

        service.addTempOrder(testBook1, session);

        verify(session, times(1)).setAttribute("tempBooks", tempBooks);
    }


    @Test
    void removeTempOrders(){
        doNothing().when(session).removeAttribute("tempBooks");

        service.removeTempOrders(session);

        verify(session, times(1)).removeAttribute("tempBooks");
    }


}

