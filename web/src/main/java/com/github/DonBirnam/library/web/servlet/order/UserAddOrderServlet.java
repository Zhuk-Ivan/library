package com.github.DonBirnam.library.web.servlet.order;

import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.OrderService;
import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;
import com.github.DonBirnam.library.service.impl.DefaultOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/userAddOrderServlet")
public class UserAddOrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("errorOrder");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookStatus currentStatus = BookStatus.valueOf(req.getParameter("status"));
        if (currentStatus.equals(BookStatus.FREE)) {
            Long bookId = Long.valueOf(req.getParameter("id"));
            Book book = bookService.find(bookId);
            Long userId = Long.valueOf(req.getParameter("userId"));
            AuthUser authUser = authUserService.getById(userId);
            LocalDateTime createOrder = LocalDateTime.now();

            BookStatus status = BookStatus.OCCUPIED;
            bookService.updateBookStatus(status, bookId);

            Order order = new Order(null, book, authUser, createOrder, null, null);
            orderService.save(order);
            redirect("user_admin", req, resp);
        }
        else {
            String error = "Книга уже занята";
            req.setAttribute("errorOrder",error);
            redirect("user_admin", req, resp);
        }
    }
}
