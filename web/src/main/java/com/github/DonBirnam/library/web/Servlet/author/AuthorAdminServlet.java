package com.github.DonBirnam.library.web.Servlet.author;


import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.service.AuthorService;
import com.github.DonBirnam.library.service.DefaultAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/libr_page")
public class AuthorAdminServlet extends HttpServlet {
    private AuthorService authorService = DefaultAuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.getAuthors();
        req.setAttribute("authors",authors);
        forward("libr_page", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
