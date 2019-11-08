package com.github.DonBirnam.library.dao.converter;

import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.model.Book;

public class BookConverter {
    public static BookEntity toEntity(Book book){
        if (book == null) {
            return null;
        }
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthorEntity(AuthorConverter.toEntity(book.getAuthor()));
        bookEntity.setPageCount(book.getPageCount());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setStatus(book.getStatus());
        bookEntity.setInStock(book.getInStock());
        return bookEntity;
        }

        public static Book fromEntity(BookEntity bookEntity){
            if (bookEntity == null) {
                return null;
            }
            return new Book (
                    bookEntity.getId(),
                    bookEntity.getTitle(),
                    AuthorConverter.fromEntity(bookEntity.getAuthorEntity()),
                    bookEntity.getPageCount(),
                    bookEntity.getIsbn(),
                    bookEntity.getGenre(),
                    bookEntity.getStatus(),
                    bookEntity.getInStock());
            }
}
