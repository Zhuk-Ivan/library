package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.converter.BookConverter;
import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.dao.repository.AuthorRepository;
import com.github.DonBirnam.library.dao.repository.BookRepository;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class DefaultBookDao implements BookDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DefaultBookDao(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Long createBook(Book book) {
        BookEntity bookEntity = BookConverter.toEntity(book);
        final AuthorEntity authorEntity = authorRepository.getOne(book.getAuthorId());
        bookEntity.setAuthorEntity(authorEntity);
        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    @Override
    public BookFull findById(Long id) {
        final BookEntity bookEntity = bookRepository.getOne(id);
        return BookConverter.fromEntity(bookEntity);
    }

    @Override
    public BookFull findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .map(BookConverter::fromEntity)
                .orElse(null);
    }


    @Override
    public void updateBook(Book book, Long id) {
        bookRepository.update(id, book.getTitle(), book.getPageCount(), book.getIsbn(), book.getGenre(), book.getInStock());
    }

    @Override
    public void updateBookStatus(BookStatus status, Long id) {
        bookRepository.updateBookStatus(status, id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public List<BookFull> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return BookConverter.fromEntityList(books);
    }


    @Override
    public List<BookFull> getBooksByGenre(Genre genre) {
        List<BookEntity> books = bookRepository.findBookEntitiesByGenre(genre);
        return BookConverter.fromEntityList(books);
    }

    @Override
    public int countBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.size();
    }

    @Override
    public List<BookFull> paging(int pageNumber, int size) {
        Page<BookEntity> booksPage = bookRepository.findAll(PageRequest.of(pageNumber, size));
        List<BookEntity> books = booksPage.getContent();
        return BookConverter.fromEntityList(books);
    }

    @Override
    public List<BookFull> findByAuthorId(Long id) {
        List<BookEntity> books = bookRepository.findByAuthorId(id);
        return BookConverter.fromEntityList(books);
    }

    @Override
    public LocalDateTime nearestDateToReturn(Long id) {
        return bookRepository.findNearestDate(id);
    }

}

