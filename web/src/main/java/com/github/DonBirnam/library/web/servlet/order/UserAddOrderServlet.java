package com.github.DonBirnam.library.web.servlet.order;


import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.OrderService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;
import com.github.DonBirnam.library.service.impl.DefaultOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/userAddOrderServlet")
public class UserAddOrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("errorOrder");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        Long userId = authUser.getId();
        Set<BookFull> orderBooks = orderService.getTempOrders();
        Set<Long> booksId = new HashSet<>();
        for (BookFull bookFull: orderBooks) {
            booksId.add(bookFull.getId());

        }
        LocalDateTime createOrder = LocalDateTime.now();
        Order order = new Order(null, booksId, userId, createOrder, null, null);
        orderService.save(order);

        redirect("user_admin", req, resp);



//        BookStatus currentStatus = BookStatus.valueOf(req.getParameter("status"));
//        if (currentStatus.equals(BookStatus.FREE)) {
//            Long bookId = Long.valueOf(req.getParameter("id"));
//            Long userId = Long.valueOf(req.getParameter("userId"));
//            Long inStock = Long.valueOf(req.getParameter("inStock"));
//            LocalDateTime createOrder = LocalDateTime.now();
//
//            Order order = new Order(null,bookId, userId, createOrder, null, null);
//            orderService.save(order);
//            if (inStock == 0) {
//                BookStatus status = BookStatus.OCCUPIED;
//                bookService.updateBookStatus(status, bookId);
//            }
//            redirect("user_admin", req, resp);
//        }
//        else {
//            String error = "Книга отсутствует в библиотеке";
//            req.setAttribute("errorOrder",error);
//            redirect("user_admin", req, resp);
//        }
    }
}
