package com.github.DonBirnam.library.web.controller;


import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.Role;
import com.github.DonBirnam.library.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final AuthUserService service;

    public LoginController(AuthUserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginGet(HttpServletRequest req) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || "anonymousUser".equals(auth.getPrincipal())) {
            return "login";
        } else {
                return "redirect:/main";
            }
        }


    @PostMapping("/login")
    public String loginPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        AuthUser authUser = service.getByLogin(login);
        if (authUser == null) {
            log.warn("user {} couldn't log in with password {}", login);
            String error = "Неверный логин либо пароль";
            req.setAttribute("errorLoginPassMessage", error);
            return "login";
        }
        log.info("user {} logged", login);
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser));
        SecurityContextHolder.getContext().setAuthentication(auth);
        req.getSession().setAttribute("user", authUser);
        if (authUser.getRole().equals(Role.BLOCKED)) {
                String error = "Вы не вернули книгу и были заблокированы";
                req.setAttribute("errorBlock", error);
                return "login";
            }
        return "redirect:/main";
    }

    private List<GrantedAuthority> getAuthorities(AuthUser authUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = String.valueOf(authUser.getRole());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
