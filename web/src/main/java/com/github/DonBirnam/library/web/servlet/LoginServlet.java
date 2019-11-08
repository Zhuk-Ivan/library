package com.github.DonBirnam.library.web.servlet;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
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
        UserDTO UserDTO = (UserDTO) req.getSession().getAttribute("authUser");
        if (UserDTO == null) {
            forward("login", req, resp);
            return;
        } else {
            if (UserDTO.getRole().equals(Role.USER)) {
                redirect("user_admin", req, resp);
            } else {
                redirect("librarian", req, resp);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        if (userService.isExist(login)) {
            UserDTO user = userService.getByLogin(login);
            req.getSession().setAttribute("authUser", user);
            log.info("user {} logged", userService.getByLogin(login).getLogin());
            if (user.getRole().equals(Role.USER)){
                redirect("user_admin",req,resp);
            }
            else if (user.getRole().equals(Role.LIBRARIAN)){
                redirect("librarian",req,resp);
            } else if (user.getRole().equals(Role.BLOCKED)){
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