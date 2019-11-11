package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public interface BookService {

    Long save(Book book);

    Book find(Long id);

    void update(Book book);

    void updateBookStatus(BookStatus status, Long id);

    void delete(Long id);

    List<Book> getAllBooks();

    List<Book> getByGenre(Genre genre);

    boolean notLastPage(int page);

}
