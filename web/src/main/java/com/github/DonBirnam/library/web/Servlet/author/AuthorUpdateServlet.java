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

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/updateAuthor")
public class AuthorUpdateServlet extends HttpServlet {
    private AuthorService authorService = DefaultAuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Author author = new Author(id,firstName,lastName);
        authorService.update(author);
        redirect("libr_page",req,resp);
    }
}
