package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public interface BookDao {

    Long createBook(Book book);

    Book findById(Long id);

    Book findByTitle(String title);

    void updateBook(Book book);

    void updateBookStatus(BookStatus status, Long id);

    void deleteBook(Long id);

    List<Book> getAllBooks();

    List<Book> getBooksByGenre(Genre genre);

    int countBooks();

}
