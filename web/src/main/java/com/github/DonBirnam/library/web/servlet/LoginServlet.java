package com.github.DonBirnam.library.web.servlet;


import com.github.DonBirnam.library.service.DefaultUserService;
import com.github.DonBirnam.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.forward;
import static com.github.DonBirnam.library.web.WebUtils.redirect;


@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object authUser = req.getSession().getAttribute("authUser");
        if (authUser == null) {
            forward("login", req, resp);
            return;
        }
       redirect("home", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.isExist(login, password)) {
            req.getSession().setAttribute("authUser", userService.getByLogin(login));
            req.getSession().setAttribute("role",userService.getByLogin(login).getRole());
            log.info("user {} logged", userService.getByLogin(login).getLogin());
            redirect("home",req,resp);
        } else {
            log.warn("user {} couldn't log in with password {}", login, password);
            forward("login", req, resp);
            String error = "Incorrect login or password";
            req.setAttribute("errorLoginPassMessage",error);
        }

    }
}