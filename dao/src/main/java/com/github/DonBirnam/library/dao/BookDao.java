package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);

    Book findById(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);

    List <Book> getAllBooks();

    List<Book> getAllAuthorBooks();

}
