package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import java.time.LocalDateTime;
import java.util.List;

public interface BookService {

    Long save(Book book);

    BookFull find(Long id);

    void update(Book book, Long id);

    void updateBookStatus(BookStatus status, Long id);

    void delete(Long id);

    List<BookFull> getAllBooks();

    List<BookFull> getByGenre(Genre genre);

    LocalDateTime getNearestDateToReturn(Long id);

    List<BookFull> findByAuthorId(Long id);

    List<BookFull> paging (Integer pageNumber);

    boolean isNotLastPage(Integer pageNumber);

}
