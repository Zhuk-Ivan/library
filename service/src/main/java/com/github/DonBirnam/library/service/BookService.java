package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public interface BookService {

    void save(Book book);

    Book find(Long id);

    void update(Book book);

    void delete(Long id);

    List<Book> getAllBooks();

    List<Book> getAllAuthorsBooks();

    Genre setGenre(String genre);

}
