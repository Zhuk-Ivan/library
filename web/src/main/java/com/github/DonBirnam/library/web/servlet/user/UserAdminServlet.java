package com.github.DonBirnam.library.web.servlet.user;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/user_admin")
public class UserAdminServlet extends HttpServlet {
    private BookService bookService = DefaultBookService.getInstance();
//    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("authUser");
        Long id = user.getId();
        req.getSession().setAttribute("userId",id);
        req.setAttribute("user", user);

        List<Book> books = bookService.getAllBooks();
        req.setAttribute("books",books);

        forward("user_admin", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

