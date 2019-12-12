package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {

    @Autowired
    private OrderService service;

    public LogoutController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest rq) {
        service.removeTempOrders();
        rq.getSession().removeAttribute("authUser");
        rq.getSession().invalidate();
        return "redirect:/login";
    }
}
