//package com.github.DonBirnam.library.web.servlet.book;
//
//import com.github.DonBirnam.library.model.Book;
//import com.github.DonBirnam.library.service.BookService;
//import com.github.DonBirnam.library.service.impl.DefaultBookService;
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
//@WebServlet(urlPatterns = "/addBook")
//public class BookAddServlet extends HttpServlet {
//    private BookService bookService = DefaultBookService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String title = req.getParameter("title");
//        Long authorId = Long.valueOf(req.getParameter("author_id"));
//        int pageCount = Integer.valueOf(req.getParameter("page_count"));
//        String isbn = req.getParameter("isbn");
//        String genre = req.getParameter("genre");
//        String staus = "Свободна";
//
//        Book book = new Book(null,title,authorId,pageCount,isbn,genre,staus);
//        bookService.save(book);
//        redirect("books_page",req,resp);
//    }
//
//}
