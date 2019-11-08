//package com.github.DonBirnam.library.web.servlet.order;
//
//import com.github.DonBirnam.library.model.OrderSaveDTO;
//import com.github.DonBirnam.library.model.UserRegDTO;
//import com.github.DonBirnam.library.service.OrderService;
//import com.github.DonBirnam.library.service.impl.DefaultOrderService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static com.github.DonBirnam.library.web.WebUtils.forward;
//
//@WebServlet(urlPatterns = "/user_orders")
//public class UserOrderServlet extends HttpServlet {
//    private OrderService orderService = DefaultOrderService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserRegDTO authUser = (UserRegDTO) req.getSession().getAttribute("authUser");
//        Long userId = authUser.getId();
//        List<OrderSaveDTO> orders = orderService.getOrdersByUserId(userId);
//        req.setAttribute("orders", orders);
//        forward("user_orders", req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//}
