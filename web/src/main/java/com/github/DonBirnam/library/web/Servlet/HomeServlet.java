package com.github.DonBirnam.library.web.Servlet;

import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.DefaultUserService;
import com.github.DonBirnam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authUser = (User)req.getSession().getAttribute("authUser");
        userService.getByLogin(authUser.getLogin());
        req.setAttribute("user",authUser);
        forward("home", req, resp);
    }



}

