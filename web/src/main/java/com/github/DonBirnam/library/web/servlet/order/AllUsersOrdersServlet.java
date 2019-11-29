package com.github.DonBirnam.library.web.servlet.order;

import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.OrderFin;
import com.github.DonBirnam.library.service.OrderService;
import com.github.DonBirnam.library.service.impl.DefaultOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/users_orders")
public class AllUsersOrdersServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderFin> orders = orderService.getAllOrders();
        Map<Long, Set<BookFull>> booksMap = new HashMap<>();
        for (OrderFin order:orders) {
            booksMap.put(order.getId(), order.getBooks());
            }

        req.setAttribute("orders", orders);
        req.setAttribute("books", booksMap);

        forward("users_orders", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
