package com.github.DonBirnam.library.web.servlet.librarian;

import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.impl.DefaultAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/librarianDelete")
public class LibrarianDeleteServlet extends HttpServlet {
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        authUserService.deleteAuthUser(id);
        redirect("librarian",req,resp);
    }
}
