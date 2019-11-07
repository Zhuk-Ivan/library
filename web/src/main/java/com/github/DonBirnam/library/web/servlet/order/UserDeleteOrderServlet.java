//package com.github.DonBirnam.library.web.servlet.order;
//
//import com.github.DonBirnam.library.service.BookService;
//import com.github.DonBirnam.library.service.OrderService;
//import com.github.DonBirnam.library.service.impl.DefaultBookService;
//import com.github.DonBirnam.library.service.impl.DefaultOrderService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static com.github.DonBirnam.library.web.WebUtils.redirect;
//
//@WebServlet(urlPatterns = "/userDeleteOrder")
//public class UserDeleteOrderServlet  extends HttpServlet {
//    private OrderService orderService = DefaultOrderService.getInstance();
//    private BookService bookService = DefaultBookService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long id = Long.valueOf(req.getParameter("id"));
//        orderService.delete(id);
//        String status = "Свободна";
//        Long bookId = Long.valueOf(req.getParameter("bookId"));
//        bookService.updateBookStatus(status,bookId);
//
//        redirect("user_admin",req,resp);
//    }
//}
