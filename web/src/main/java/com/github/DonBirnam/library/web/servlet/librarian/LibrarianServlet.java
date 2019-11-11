package com.github.DonBirnam.library.web.servlet.librarian;

import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.impl.DefaultUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/librarian")

public class LibrarianServlet extends HttpServlet {
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserFull> users = userService.getAllUsers();
        req.setAttribute("users", users);
        forward("librarian", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
