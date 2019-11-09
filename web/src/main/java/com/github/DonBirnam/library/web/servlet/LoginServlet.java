package com.github.DonBirnam.library.web.servlet;


import com.github.DonBirnam.library.model.AuthUser;
import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;
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
    AuthUserService service = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (authUser == null) {
            forward("login", req, resp);
            return;
        } else {
            if (authUser.getRole().equals(Role.USER)) {
                redirect("user_admin", req, resp);
            } else {
                redirect("librarian", req, resp);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        if (service.isExist(login)) {
            AuthUser authUser = service.getByLogin(login);
            req.getSession().setAttribute("authUser", authUser);
            log.info("user {} logged", login);
            if (authUser.getRole().equals(Role.USER)){
                redirect("user_admin",req,resp);
            }
            else if (authUser.getRole().equals(Role.LIBRARIAN)){
                redirect("librarian",req,resp);
            } else if (authUser.getRole().equals(Role.BLOCKED)){
                String error = "Вы не вернули книгу и были заблокированы";
                req.setAttribute("errorBlock",error);
                forward("login", req, resp);
            }
        } else {
            log.warn("user {} couldn't log in with password {}", login);
            String error = "Неверный логин либо пароль";
            req.setAttribute("errorLoginPassMessage",error);
            forward("login", req, resp);
        }

    }
}