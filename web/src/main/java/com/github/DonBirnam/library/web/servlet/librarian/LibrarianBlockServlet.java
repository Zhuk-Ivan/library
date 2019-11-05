package com.github.DonBirnam.library.web.servlet.librarian;

import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.impl.DefaultUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/librarianBlock")
public class LibrarianBlockServlet extends HttpServlet {
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String currentRole = req.getParameter("role");
        if (currentRole.equals("user")){
            String role = "blocked";
            userService.block(role,id);
            redirect("librarian",req,resp);
        } else {
            String role = "user";
            userService.block(role,id);
            redirect("librarian",req,resp);
        }
    }
}
