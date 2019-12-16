package com.github.DonBirnam.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest rq) {
        rq.getSession().removeAttribute("authUser");
        rq.getSession().removeAttribute("tempBooks");
        rq.getSession().invalidate();
        return "redirect:/login";
    }
}
