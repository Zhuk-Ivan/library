package com.github.DonBirnam.library.web.servlet.book;

import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.service.AuthorService;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.impl.DefaultAuthorService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.DonBirnam.library.web.WebUtils.redirect;

@WebServlet(urlPatterns = "/addBook")
public class BookAddServlet extends HttpServlet {
    private BookService bookService = DefaultBookService.getInstance();
    private AuthorService authorService = DefaultAuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String authorFN = req.getParameter("authorFN");
        String authorLN = req.getParameter("authorLN");
        int pageCount = Integer.valueOf(req.getParameter("page_count"));
        String isbn = req.getParameter("isbn");
        Genre genre = Genre.valueOf(req.getParameter("genre"));
        BookStatus staus = BookStatus.FREE;

        if (authorService.findByLastName(authorLN) == null){
            Author authorSave = new Author(null,authorFN,authorLN);
            authorService.save(authorSave);
            Author savedAuthor = authorService.findByLastName(authorSave.getLastName());
            Book book = new Book(null,title,savedAuthor,pageCount,isbn,genre,staus);
            bookService.save(book);
        }
        else {
            Author author = authorService.findByLastName(authorLN);
            Book book = new Book(null,title,author,pageCount,isbn,genre,staus);
            bookService.save(book);
        }
        redirect("books_page",req,resp);
    }

}
