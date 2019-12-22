package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.model.User.*;
import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private BookService bookService;

    public UserController(UserService userService, AuthUserService authUserService, BookService bookService) {
        this.userService = userService;
        this.authUserService = authUserService;
        this.bookService = bookService;
    }


    @GetMapping ("/personal_details")
    public String myPersonalData(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("user");
        UserFull user = userService.getUserById(authUser.getId());
        req.setAttribute("user", user);
        return "personal_details";
    }


    @GetMapping("/users")
    public String getAllUsers(HttpServletRequest req) {
        List<UserFull> users = userService.getAllUsers();
        req.setAttribute("users", users);

        List<BlockedUser> blockedUsers = userService.getAllBlockedUsers();
        req.setAttribute("blockedUsers", blockedUsers);
        return "users";
    }

    @PostMapping("/block")
    @Secured("ROLE_LIBRARIAN")
    public String blockUser(HttpServletRequest req) {
        String login = req.getParameter("login");
        Long authUserId = authUserService.getByLogin(login).getId();
        Role currentRole = Role.valueOf(req.getParameter("role"));
        if (currentRole.equals(Role.USER)){
            Role role = Role.BLOCKED;
            authUserService.block(role,authUserId);
            return "redirect:/users";
        } else {
            Role role = Role.USER;
            authUserService.block(role,authUserId);
            return "redirect:/users";
        }
    }

    @PostMapping("/deleteUser")
    @Secured("ROLE_LIBRARIAN")
    public String deleteUser(HttpServletRequest req) {
        String login = req.getParameter("login");
        Long authUserId = authUserService.getByLogin(login).getId();
        authUserService.deleteAuthUser(authUserId);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updatePersonalData(HttpServletRequest req) {

        Long id = Long.valueOf(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");

        User user = new User(id,firstName,lastName,phone,null,null);
        userService.updateUser(user, id);
        return "redirect:/personal_details";
    }

}
