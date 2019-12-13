//package com.github.DonBirnam.library.web.servlet.book;
//
//import com.github.DonBirnam.library.model.Author;
//import com.github.DonBirnam.library.model.Book;
//import com.github.DonBirnam.library.model.BookStatus;
//import com.github.DonBirnam.library.model.Genre;
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
//@WebServlet(urlPatterns = "/updateBook")
//public class BookUpdateServlet extends HttpServlet {
//    private BookService bookService = DefaultBookService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long id = Long.valueOf(req.getParameter("id"));
//        String title = req.getParameter("title");
//        Long authorId = Long.valueOf(req.getParameter("authorId"));
//        String authorFN = req.getParameter("authorFN");
//        String authorLN = req.getParameter("authorLN");
//        Author author = bookService.find(id).getAuthor();
//        author.setId(authorId);
//        author.setFirstName(authorFN);
//        author.setLastName(authorLN);
//        int pageCount = Integer.valueOf(req.getParameter("page_count"));
//        String isbn = req.getParameter("isbn");
//        Genre genre = Genre.valueOf(req.getParameter("genre"));
//        BookStatus status = BookStatus.valueOf(req.getParameter("status"));
//        int inStock = Integer.valueOf(req.getParameter("inStock"));
//        Book book = new Book(id,title,author,pageCount,isbn,genre,status, inStock);
//        bookService.update(book);
//        redirect("books_page",req,resp);
//    }
//}
//
