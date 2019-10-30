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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = DefaultUserService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("registration", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = "user";


        if (!userService.isExist(login, password)) {
            userService.saveUser(new User(null,firstName,lastName,phone,email,login,password,role));
            req.setAttribute("login", login);
            log.info("user {} was created with the following fields {},{},{},{}", login, firstName, lastName, email, phone);
            forward("login", req, resp);
        }
        else {
            forward("registration", req, resp);
        }
    }
}