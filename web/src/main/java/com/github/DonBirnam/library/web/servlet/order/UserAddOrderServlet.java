package com.github.DonBirnam.library.web.servlet.order;

import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.service.OrderService;
import com.github.DonBirnam.library.service.impl.DefaultOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/UserAddOrderServlet")
public class UserAddOrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long bookId = Long.valueOf(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute("userId");
        LocalDateTime takeDate = LocalDateTime.now();
        LocalDateTime expireDate = takeDate.plusDays(7L);

        Order order = new Order(null, bookId, userId, takeDate, expireDate);
        orderService.save(order);
        redirect("user_admin", req, resp);
    }
}
