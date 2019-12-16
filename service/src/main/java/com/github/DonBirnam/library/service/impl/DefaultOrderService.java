package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderDao orderDao;
    private final AuthUserDao authUserDao;

    public DefaultOrderService(OrderDao orderDao, AuthUserDao authUserDao) {
        this.orderDao = orderDao;
        this.authUserDao = authUserDao;
    }

    @Override
    @Transactional
    public void save(Order order, HttpSession session) {
        orderDao.createOrder(order);
        removeTempOrders(session);
    }


    @Override
    @Transactional
    public OrderFin find(Long id) {
        return orderDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderDao.deleteOrder(id);
    }

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

    @Override
    @Transactional
    public List<OrderFin> getOrdersByUserId(Long userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public void addTempOrder(BookFull bookFull, HttpSession session) {
        if (session.getAttribute("tempBooks") == null){

            Set<BookFull> tempBooks = new HashSet<>();
            tempBooks.add(bookFull);
            session.setAttribute("tempBooks", tempBooks);
        }
        else {
            Set<BookFull> tempBooks = (Set<BookFull>)session.getAttribute("tempBooks");
            tempBooks.add(bookFull);
            session.setAttribute("tempBooks", tempBooks);
        }
    }

    @Override
    public Set<BookFull> getTempOrders(HttpSession session) {
         return (Set<BookFull>)session.getAttribute("tempBooks");

    }

    @Override
    @Transactional
    public boolean canMakeTempOrder(HttpSession session) {
        AuthUser authUser = (AuthUser)session.getAttribute("user");
        int booksInUserOrders = authUserDao.countBooksInOrders(authUser.getId());
        Set<BookFull> books = (Set<BookFull>)session.getAttribute("tempBooks");

        if(books == null){
            if (booksInUserOrders < 3){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (booksInUserOrders < 3 && books.size() < (booksInUserOrders-1)){
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public void removeTempOrders(HttpSession session) {
        session.removeAttribute("tempBooks");
    }

    @Override
    public void removeBookFromTempOrders(HttpSession session, BookFull bookFull) {
        Set<BookFull> tempBooks = (Set<BookFull>)session.getAttribute("tempBooks");
        tempBooks.remove(bookFull);
        session.setAttribute("tempBooks", tempBooks);
    }
}
 