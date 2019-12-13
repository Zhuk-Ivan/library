//package com.github.DonBirnam.library.web.servlet.librarian;
//
//import com.github.DonBirnam.library.model.User.Role;
//import com.github.DonBirnam.library.service.AuthUserService;
//import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static com.github.DonBirnam.library.web.WebUtils.redirect;
//
//@WebServlet(urlPatterns = "/librarianBlock")
//public class LibrarianBlockServlet extends HttpServlet {
//    private AuthUserService authUserService = DefaultAuthUserService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        Long authUserId = authUserService.getByLogin(login).getId();
//        Role currentRole = Role.valueOf(req.getParameter("role"));
//        if (currentRole.equals(Role.USER)){
//            Role role = Role.BLOCKED;
//            authUserService.block(role,authUserId);
//            redirect("librarian",req,resp);
//        } else {
//            Role role = Role.USER;
//            authUserService.block(role,authUserId);
//            redirect("librarian",req,resp);
//        }
//    }
//}
