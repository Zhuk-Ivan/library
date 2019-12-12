package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private AuthUserService authUserService;

    public RegistrationController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("/registration")
    public String doGet(HttpServletRequest req) {
        return "registration";
    }

    @PostMapping("/registration")
    public String doPost(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!authUserService.isExist(login)) {
            authUserService.save(firstName,lastName,phone,email,login,password);
            log.info("user {} was created with the following fields {},{},{},{}", login, firstName, lastName, email, phone);
            return "redirect:/login";
        }
        else {
            req.setAttribute("error", "User with this login already exists");
            return "registration";
        }
    }
}