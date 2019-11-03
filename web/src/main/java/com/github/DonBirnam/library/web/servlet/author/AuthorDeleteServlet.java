package com.github.DonBirnam.library.web.servlet.author;

import com.github.DonBirnam.library.service.AuthorService;
import com.github.DonBirnam.library.service.impl.DefaultAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/deleteAuthor")
public class AuthorDeleteServlet extends HttpServlet {
    private AuthorService authorService = DefaultAuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        authorService.delete(id);
        redirect("libr_page",req,resp);
    }
}
