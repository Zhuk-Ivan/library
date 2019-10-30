package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);

    void readBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    List <Book> showAllBooks();

}
