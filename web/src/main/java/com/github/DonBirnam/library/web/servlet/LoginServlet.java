package com.github.DonBirnam.library.web.servlet;


import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
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
        User authUser = (User) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            forward("login", req, resp);
            return;
        } else {
            if (authUser.getRole().equals("user")) {
                redirect("user_admin", req, resp);
            } else {
                redirect("librarian", req, resp);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.isExist(login, password)) {
            User user = userService.getByLogin(login);
            req.getSession().setAttribute("authUser", user);
            req.getSession().setAttribute("role",user.getRole());
            req.getSession().setAttribute("login", user.getLogin());
            log.info("user {} logged", userService.getByLogin(login).getLogin());
            if (user.getRole().equals("user")){
                redirect("user_admin",req,resp);
            }
            else if (user.getRole().equals("librarian")){
                redirect("librarian",req,resp);
            } else if (user.getRole().equals("blocked")){
                String error = "Вы не вернули книгу и были заблокированы";
                req.setAttribute("errorBlock",error);
                forward("login", req, resp);
            }
        } else {
            log.warn("user {} couldn't log in with password {}", login, password);
            String error = "Неверный логин либо пароль";
            req.setAttribute("errorLoginPassMessage",error);
            forward("login", req, resp);
        }

    }
}