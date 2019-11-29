package com.github.DonBirnam.library.web.servlet.order;

import com.github.DonBirnam.library.model.BookFull;
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
import java.util.Set;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/user_orders")
public class UserOrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<BookFull> orderBooks = orderService.getTempOrders();
        req.getSession().setAttribute("orderBooks", orderBooks);
        forward("user_orders", req, resp);


//        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
//        Long userId = authUser.getId();
//        List<OrderFin> orders = orderService.getOrdersByUserId(userId);
//        req.setAttribute("userOrders", orders);
//        forward("user_orders", req, resp);
//
//        Order order = (Order)req.getSession().getAttribute("order");
//        req.setAttribute("newOrder", order);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
