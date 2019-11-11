package com.github.DonBirnam.library.web.servlet.user;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;
import com.github.DonBirnam.library.service.impl.DefaultUserService;

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
    private UserService userService = DefaultUserService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        UserFull user = userService.getUserById(authUser.getId());
        req.setAttribute("user", user);

            int page = 1;
            req.getSession().setAttribute("page", page);
            req.getSession().setAttribute("notLast", bookService.notLastPage(page));
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books",books);

        forward("user_admin", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String navigation = req.getParameter("nav");
            if (navigation == null) {
                return;
            }
            int currentPage = (int) req.getSession().getAttribute("page");
            if (navigation.equals("Далее")) {
                currentPage++;
            } else if (navigation.equals("Назад")) {
                currentPage--;
            }

            req.getSession().setAttribute("page", currentPage);
            req.getSession().setAttribute("notLast", bookService.notLastPage(currentPage));
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
            forward("user_admin", req, resp);
        }
    }



