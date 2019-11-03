package com.github.DonBirnam.library.web.servlet;

import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
import com.github.DonBirnam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.forward;
import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/librarian")

public class LibrarianServlet extends HttpServlet {
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        req.setAttribute("users",users);
        forward("librarian", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        userService.deleteUser(login);
        redirect("librarian",req,resp);
    }
}
