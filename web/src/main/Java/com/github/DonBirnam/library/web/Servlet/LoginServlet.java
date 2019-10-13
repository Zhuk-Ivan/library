package com.github.DonBirnam.library.web.Servlet;


import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.DefaultUserService;
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
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
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
            log.info("user {} logged", userService.getByLogin(login).getLogin());
            forward("home", req, resp);
        } else {
            forward("registration", req, resp);
        }

    }
}