package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultBookService implements BookService {

    private final BookDao bookDao;

    public DefaultBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public Long save(Book book) {
       Long bookId =  bookDao.createBook(book);
       return bookId;
    }

    @Override
    @Transactional
    public BookFull find(Long id) {
        BookFull book = bookDao.findById(id);
        if (book == null) {
            return null;
        } else {
            return book;
        }
    }

    @Override
    @Transactional
    public void update(Book book, Long id) {
        bookDao.updateBook(book, id);
    }

    @Override
    @Transactional
    public void updateBookStatus(BookStatus status, Long id) {
        bookDao.updateBookStatus(status,id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookDao.deleteBook(id);
    }

    @Override
    @Transactional
    public List<BookFull> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @Transactional
    public List<BookFull> getByGenre(Genre genre) {
        return bookDao.getBooksByGenre(genre);
    }

    @Override
    @Transactional
    public int countBookPage(int size) {
        int count = bookDao.countBooks();
        return count / size + 1;
    }

    @Override
    @Transactional
    public LocalDateTime getNearestDateToReturn(Long id) {
        return bookDao.nearestDateToReturn(id);
    }

    @Override
    @Transactional
    public List<BookFull> findByAuthorId(Long id) {
        return bookDao.findByAuthorId(id);
    }

    @Override
    @Transactional
    public List<BookFull> paging(int pageNumber, int size) {
        return bookDao.paging(pageNumber,size);
    }

}
