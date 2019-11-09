package com.github.DonBirnam.library.web.servlet.book;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.model.User;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.impl.DefaultBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.github.DonBirnam.library.web.WebUtils.forward;

@WebServlet(urlPatterns = "/selectGenre")
public class BookGenreSelectServlet  extends HttpServlet {
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authUser = (User)req.getSession().getAttribute("authUser");
        Long id = authUser.getId();
        req.getSession().setAttribute("userId",id);
        req.setAttribute("user", authUser);

        Genre genre = Genre.valueOf(req.getParameter("genre"));
        if (genre.equals(Genre.ALL)){
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
            forward("user_admin",req,resp);
        } else {
            List<Book> books = bookService.getByGenre(genre);
            req.setAttribute("books", books);
            forward("user_admin", req, resp);
        }
    }
}
