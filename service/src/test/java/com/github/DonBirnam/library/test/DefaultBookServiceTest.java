package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.model.Book;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.service.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultBookServiceTest {

    private Author author = new Author(100L, "Говард", "Лавкрафт");
    private Book book = new Book(100L, "Зов Ктулху", author,480,"978-5170946105", Genre.FANTASY, BookStatus.FREE,3);
    private Book newBook = new Book(100L, "Тень над инсмаутом", author,560,"978-5170946105", Genre.FANTASY, BookStatus.FREE,3);

    @Mock
    BookDao dao;

    @InjectMocks
    DefaultBookService service;

    @Test
    void saveBook(){
        when(dao.createBook(book)).thenReturn(100L);

        Long id = service.save(book);

        assertEquals(id, 100L);

    }

    @Test
    public void getById() {
        when(dao.findById(100L)).thenReturn(book);

        assertNotNull(service.find(100L));
    }

    @Test
    void getAllBooks() {
        when(dao.getAllBooks()).thenReturn(new ArrayList<>());
        List<Book> books = service.getAllBooks();
        assertNotNull(books);
    }

    @Test
    void getAllBooksByGenre() {
        when(dao.getBooksByGenre(Genre.FANTASY)).thenReturn(new ArrayList<>());
        List<Book> books = service.getByGenre(Genre.FANTASY);
        assertNotNull(books);
        for (Book book: books) {
            assertEquals(book.getGenre(), Genre.FANTASY);
        }
    }


}



