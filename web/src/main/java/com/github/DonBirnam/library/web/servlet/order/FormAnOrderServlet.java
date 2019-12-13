//package com.github.DonBirnam.library.web.servlet.order;
//
//import com.github.DonBirnam.library.model.BookFull;
//import com.github.DonBirnam.library.model.BookStatus;
//import com.github.DonBirnam.library.model.User.UserFull;
//import com.github.DonBirnam.library.service.AuthUserService;
//import com.github.DonBirnam.library.service.BookService;
//import com.github.DonBirnam.library.service.OrderService;
//import com.github.DonBirnam.library.service.UserService;
//import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;
//import com.github.DonBirnam.library.service.impl.DefaultBookService;
//import com.github.DonBirnam.library.service.impl.DefaultOrderService;
//import com.github.DonBirnam.library.service.impl.DefaultUserService;
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
//@WebServlet(urlPatterns = "/formAnOrder")
//public class FormAnOrderServlet extends HttpServlet {
//    private BookService bookService = DefaultBookService.getInstance();
//    private AuthUserService authUserService = DefaultAuthUserService.getInstance();
//    private UserService userService = DefaultUserService.getInstance();
//    private OrderService orderService = DefaultOrderService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getAttribute("errorMakeOrder");
//        forward("user_admin", req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Long userId = Long.valueOf(req.getParameter("userId"));
//        UserFull user = userService.getUserById(userId);
//        req.setAttribute("user", user);
//
//        List<BookFull> books = bookService.getAllBooks();
//        req.setAttribute("books",books);
//
//        Long bookId = Long.valueOf(req.getParameter("id"));
//        BookFull bookToOrder = bookService.find(bookId);
//        BookStatus currentStatus = BookStatus.valueOf(req.getParameter("status"));
//
//
//        if (currentStatus.equals(BookStatus.FREE)) {
//            if (authUserService.canMakeAnOrder(userId)) {
//                orderService.addTempOrder(bookToOrder);
//                forward("user_admin", req, resp);
//
//            } else {
//                String error = "Вы не можете взять более трех книг";
//                req.setAttribute("errorMakeOrder", error);
//                forward("user_admin", req, resp);
//            }
//        } else {
//            String error = "Книга отсутствует в библиотеке";
//            req.setAttribute("errorOrder", error);
//            forward("user_admin", req, resp);
//        }
//    }
//}
