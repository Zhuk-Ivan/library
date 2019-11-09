package com.github.DonBirnam.library.web.servlet;

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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private AuthUserService service = DefaultAuthUserService.getInstance();

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

        if (!service.isExist(login)) {
            service.save(firstName,lastName,phone,email,login,password);
            log.info("user {} was created with the following fields {},{},{},{}", login, firstName, lastName, email, phone);
            redirect("login", req, resp);
        }
        else {
            req.setAttribute("error", "User with this login already exists");
            forward("registration", req, resp);
        }
    }
}