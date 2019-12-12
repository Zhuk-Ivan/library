package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public interface BookDao {

    Long createBook(Book book);

    BookFull findById(Long id);

    BookFull findByTitle(String title);

    void updateBook(Book book, Long id);

    void updateBookStatus(BookStatus status, Long id);

    void deleteBook(Long id);

    List<BookFull> getAllBooks();

    List<BookFull> getBooksByGenre(Genre genre);

    int countBooks();

    List<BookFull> paging(int pageNumber, int size);

}
