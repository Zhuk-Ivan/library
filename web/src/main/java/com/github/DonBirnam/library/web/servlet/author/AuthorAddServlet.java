//package com.github.DonBirnam.library.web.servlet.author;
//
//
//import com.github.DonBirnam.library.model.Author;
//import com.github.DonBirnam.library.service.AuthorService;
//import com.github.DonBirnam.library.service.impl.DefaultAuthorService;
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
//@WebServlet(urlPatterns = "/addAuthor")
//public class AuthorAddServlet extends HttpServlet {
//    private AuthorService authorService = DefaultAuthorService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String fitstName = req.getParameter("firstName");
//        String lastName = req.getParameter("lastName");
//        Author author = new Author(null,fitstName,lastName);
//        authorService.save(author);
//        redirect("libr_page",req,resp);
//    }
//}
