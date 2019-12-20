package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.model.*;
import com.github.DonBirnam.library.service.AuthorService;
import com.github.DonBirnam.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    int pageNumber = 1;
    int size = 5;

    @GetMapping("/main")
    public String doGet(HttpServletRequest req) {

        List<BookFull> books = bookService.paging(pageNumber, size);
        Map<Long, String> nearestDatesToReturn = new HashMap<>();
        for (BookFull book: books) {
            if (book.getStatus().equals(BookStatus.OCCUPIED)){
                LocalDateTime nearestDate = bookService.getNearestDateToReturn(book.getId());
                if (nearestDate != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
                    String date = "Книга будет доступна " + nearestDate.format(formatter);
                    nearestDatesToReturn.put(book.getId(), date);
                }
                else {
                    String message = "Все книги забронированы";
                    nearestDatesToReturn.put(book.getId(), message);
                }
            }
        }

        int maxResult = bookService.countBookPage(size);
        req.setAttribute("dates",nearestDatesToReturn);
        req.setAttribute("pageNumber",pageNumber);
        req.setAttribute("maxResult",maxResult);
        req.setAttribute("books",books);

        return "main";
    }

    @PostMapping("/main")
    public String doPost(HttpServletRequest req) {
        if (req.getParameter("next") != null) {
            pageNumber++;
            req.setAttribute("pageNumber", pageNumber);
            List<BookFull> books = bookService.paging(pageNumber, size);
            req.setAttribute("books", books);
        }

        if (req.getParameter("prev") != null) {
            pageNumber--;
            req.setAttribute("pageNumber", pageNumber);
            List<BookFull> books = bookService.paging(pageNumber, size);
            req.setAttribute("books", books);
        }
        return "redirect:/main";
    }


    @PostMapping("/addBook")
    @Secured("ROLE_LIBRARIAN")
    public String addBook(HttpServletRequest req) {
        String title = req.getParameter("title");
        String authorFN = req.getParameter("authorFN");
        String authorLN = req.getParameter("authorLN");
        int pageCount = Integer.valueOf(req.getParameter("page_count"));
        String isbn = req.getParameter("isbn");
        Genre genre = Genre.valueOf(req.getParameter("genre"));
        int inStock = Integer.valueOf(req.getParameter("inStock"));
        BookStatus staus = BookStatus.FREE;

        if (authorService.findByFullName(authorFN, authorLN) == null){
            Author authorSave = new Author(null,authorFN,authorLN);
            Long authorId = authorService.save(authorSave);
            Book book = new Book(null,title,pageCount,isbn,genre,staus,inStock, authorId);
            bookService.save(book);
        }
        else {
            Author author = authorService.findByFullName(authorFN, authorLN);
            Long authorId = author.getId();
            Book book = new Book(null,title,pageCount,isbn,genre,staus, inStock, authorId);
            bookService.save(book);
        }
//        redirect("books_page",req,resp);
        return "redirect:/main";
    }

    @PostMapping("/deleteBook")
    @Secured("ROLE_LIBRARIAN")
    public String deleteBook(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        bookService.delete(id);
        return "redirect:/main";
    }

    @PostMapping("/findBooksByAuthorId")
    public String findBooksByAuthorId(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        List<BookFull> authorBooks = bookService.findByAuthorId(id);

        List<Author> authors = authorService.getAuthors();

        req.setAttribute("authors",authors);
        req.setAttribute("authorBooks", authorBooks);
        return "authors";
    }

    @PostMapping("/select")
    public String selectByGenre(HttpServletRequest req) {

        Genre genre = Genre.valueOf(req.getParameter("genre"));
        if (genre.equals(Genre.ALL)) {
            List<BookFull> books = bookService.paging(pageNumber, size);
            int maxResult = bookService.countBookPage(size);
            req.setAttribute("pageNumber",pageNumber);
            req.setAttribute("maxResult",maxResult);
            req.setAttribute("books",books);
            return "main";
        } else {
            List<BookFull> books = bookService.getByGenre(genre);
            req.setAttribute("books", books);
            return "main";
        }
    }

    @PostMapping("/updateBook")
    @Secured("ROLE_LIBRARIAN")
    public String updateBook(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");

        String authorFN = req.getParameter("authorFirstName");
        String authorLN = req.getParameter("authorLastName");

        int pageCount = Integer.valueOf(req.getParameter("page_count"));
        String isbn = req.getParameter("isbn");
        Genre genre = Genre.valueOf(req.getParameter("genre"));
        BookStatus status = BookStatus.valueOf(req.getParameter("status"));
        int inStock = Integer.valueOf(req.getParameter("inStock"));

        Author author = authorService.findByFullName(authorFN, authorLN);

        Book book = new Book(id, title, pageCount, isbn, genre, status, inStock, author.getId());
        bookService.update(book, id);
        return "redirect:/main";
    }


}

