package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public interface BookService {

    void save(Book book);

    Book find(Long id);

    void update(Book book);

    void updateBookStatus(String status, Long id);

    void delete(Long id);

    List<Book> getAllBooks();

    List<Book> getAllAuthorsBooks();

    List<Book> getByGenre(String genre);

    Genre setGenre(String genre);

}
