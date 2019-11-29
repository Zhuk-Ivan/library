package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.impl.DefaultBookDao;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.service.BookService;

import java.util.List;

public class DefaultBookService implements BookService {

    private BookDao bookDao = DefaultBookDao.getInstance();

    private static class SingletonHolder {
        static final BookService HOLDER_INSTANCE = new DefaultBookService();
    }

    public static BookService getInstance(){
        return DefaultBookService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Long save(Book book) {
       Long bookId =  bookDao.createBook(book);
       return bookId;
    }

    @Override
    public BookFull find(Long id) {
        BookFull book = bookDao.findById(id);
        if (book == null) {
            return null;
        } else {
            return book;
        }
    }

    @Override
    public void update(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void updateBookStatus(BookStatus status, Long id) {
        bookDao.updateBookStatus(status,id);
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteBook(id);
    }

    @Override
    public List<BookFull> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public List<BookFull> getByGenre(Genre genre) {
        return bookDao.getBooksByGenre(genre);
    }

    @Override
    public boolean notLastPage(int page) {
        int max = 2;
        int countBooks = bookDao.countBooks();
        int lastPage = countBooks / max;
        return page < lastPage;
    }


}
