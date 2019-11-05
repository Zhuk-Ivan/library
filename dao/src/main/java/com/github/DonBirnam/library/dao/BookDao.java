package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);

    Book findById(Long id);

    void updateBook(Book book);

    void updateBookStatus(String status, Long id);

    void deleteBook(Long id);

    List <Book> getAllBooks();

    List<Book> getAllAuthorBooks();

    List<Book> getBooksByGenre(String genre);

}
