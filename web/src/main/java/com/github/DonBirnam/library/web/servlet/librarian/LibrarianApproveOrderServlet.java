package com.github.DonBirnam.library.web.servlet.librarian;

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

@WebServlet(urlPatterns = "/librarian_approve")
public class LibrarianApproveOrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        LocalDateTime takeDate = LocalDateTime.now();
        LocalDateTime expireDate = takeDate.plusDays(7);
        orderService.approve(takeDate,expireDate,id);
        redirect("users_orders",req,resp);

    }

}
