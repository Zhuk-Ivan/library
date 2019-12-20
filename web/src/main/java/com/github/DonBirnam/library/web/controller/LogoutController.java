package com.github.DonBirnam.library.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest rq) {
        SecurityContextHolder.clearContext();
        try {
            rq.logout();
        } catch (ServletException e){
            throw new RuntimeException();
        }
        rq.getSession().removeAttribute("tempBooks");
        rq.getSession().invalidate();
        return "login";
    }
}

