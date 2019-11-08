package com.github.DonBirnam.library.web.servlet;

import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;
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
        Role role = Role.USER;

        if (!userService.isExist(login)) {
            UserRegDTO userRegDTO = new UserRegDTO(null,firstName,lastName,phone,email,login,password,role);
            UserDTO user = userService.saveUser(userRegDTO);
            req.setAttribute("login", login);
            log.info("user {} was created with the following fields {},{},{},{}", login, firstName, lastName, email, phone);
            forward("login", req, resp);
        }
        else {
            forward("registration", req, resp);
        }
    }
}