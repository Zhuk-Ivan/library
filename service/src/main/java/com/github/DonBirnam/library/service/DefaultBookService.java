package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.impl.DefaultBookDao;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.Genre;

import java.util.List;

public class DefaultBookService implements BookService {

    BookDao bookDao = DefaultBookDao.getInstance();

    private static class SingletonHolder {
        static final BookService HOLDER_INSTANCE = new DefaultBookService();
    }

    public static BookService getInstance(){
        return DefaultBookService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void save(Book book) {
        bookDao.createBook(book);
    }

    @Override
    public Book find(Long id) {
       Book book = bookDao.findById(id);
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
    public void delete(Long id) {
        bookDao.deleteBook(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Genre setGenre(String genre) {
        Genre type = null;
        switch (genre){
            case "детектив": type=Genre.DETECTIVE;
                break;
            case "фантастика": type=Genre.FANTASY;
                break;
            case "любовный роман": type=Genre.LOVE;
        }

        return type;
    }
}
