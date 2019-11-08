package com.github.DonBirnam.library.web.servlet.book;


import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.*;

@WebServlet(urlPatterns = "/books_page")
public class AdminBookServlet extends HttpServlet {
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        req.setAttribute("books",books);
        forward("books_page", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect("books_page", req, resp);
    }
}
