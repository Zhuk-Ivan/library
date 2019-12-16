package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookFull;

import java.util.ArrayList;
import java.util.List;

public class BookConverter{



    public static BookEntity toEntity(Book book){
        if (book == null) {
            return null;
        }
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPageCount(book.getPageCount());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setStatus(book.getStatus());
        bookEntity.setInStock(book.getInStock());
        return bookEntity;
        }

    public static BookFull fromEntity(BookEntity bookEntity){
            if (bookEntity == null) {
                return null;
            }
                return new BookFull(
                        bookEntity.getId(),
                        bookEntity.getTitle(),
                        bookEntity.getPageCount(),
                        bookEntity.getIsbn(),
                        bookEntity.getGenre(),
                        bookEntity.getStatus(),
                        bookEntity.getInStock(),
                        bookEntity.getAuthorEntity().getFirstName(),
                        bookEntity.getAuthorEntity().getLastName());

            }

    public static List<BookFull> fromEntityList(List<BookEntity> booksEntity) {
        if (booksEntity == null){
            return null;
        }
        final List<BookFull> books = new ArrayList<>();
        for (BookEntity bookEntity : booksEntity) {
            books.add(fromEntity(bookEntity));
        }
        return books;
    }
}
