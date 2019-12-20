package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthors(HttpServletRequest req) {
        List<Author> authors = authorService.getAuthors();
        req.setAttribute("authors",authors);
        return "authors";
    }

    @PostMapping("/addAuthor")
    @Secured("ROLE_LIBRARIAN")
    public String addAuthor(HttpServletRequest req) {
        String fitstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Author author = new Author(null, fitstName, lastName);
        authorService.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/updateAuthor")
    @Secured("ROLE_LIBRARIAN")
    public String updateAuthor(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Author author = new Author(null, firstName,lastName);
        authorService.update(author, id);
        return "redirect:/authors";
    }

    @PostMapping("/deleteAuthor")
    @Secured("ROLE_LIBRARIAN")
    public String deleteAuthor(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        authorService.delete(id);
        return "redirect:/authors";
    }
}
