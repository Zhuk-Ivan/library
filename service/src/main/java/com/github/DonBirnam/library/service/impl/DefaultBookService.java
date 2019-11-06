package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.impl.DefaultBookDao;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.service.BookService;

public class DefaultBookService implements BookService {

    private BookDao bookDao = DefaultBookDao.getInstance();

    private static class SingletonHolder {
        static final BookService HOLDER_INSTANCE = new DefaultBookService();
    }

    public static BookService getInstance(){
        return DefaultBookService.SingletonHolder.HOLDER_INSTANCE;
    }

//    @Override
//    public void save(Book book) {
//        bookDao.createBook(book);
//    }
//
//    @Override
//    public Book find(Long id) {
//       Book book = bookDao.findById(id);
//        if (book == null) {
//            return null;
//        } else {
//            return book;
//        }
//    }
//
//    @Override
//    public void update(Book book) {
//        bookDao.updateBook(book);
//    }

    @Override
    public void updateBookStatus(String status, Long id) {
        bookDao.updateBookStatus(status,id);
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteBook(id);
    }

//    @Override
//    public List<Book> getAllBooks() {
//        return bookDao.getAllBooks();
//    }
//
//    @Override
//    public List<Book> getAllAuthorsBooks() {
//        return bookDao.getAllAuthorBooks();
//    }
//
//    @Override
//    public List<Book> getByGenre(String genre) {
//        return bookDao.getBooksByGenre(genre);
//    }

    @Override
    public Genre setGenre(String genre) {
        Genre type = null;
        switch (genre){
            case "детектив": type=Genre.DETECTIVE;
                break;
            case "фантастика": type=Genre.FANTASY;
                break;
            case "антиутопия": type=Genre.DYSTOPIA;
                break;
            case "драма": type=Genre.DRAMA;
                break;
            case "философия": type=Genre.PHILOSOPHY;
                break;
        }

        return type;
    }
}
