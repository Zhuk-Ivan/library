package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.model.*;
import com.github.DonBirnam.library.service.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultBookServiceTest {

    private Author author = new Author(100L, "Говард", "Лавкрафт");
    private Book book = new Book(100L, "Зов Ктулху",480,"978-5170946105", Genre.FANTASY, BookStatus.FREE,3, 100L);
    private BookFull bookBD = new BookFull(100L, "Зов Ктулху",480,"978-5170946105", Genre.FANTASY, BookStatus.FREE,3,"Говард","Лавкрафт");
    private Book newBook = new Book(100L, "Тень над инсмаутом",560,"978-5170946105", Genre.FANTASY, BookStatus.FREE,3, 100L);
    private List<BookFull> books = new ArrayList<>();



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
        when(dao.findById(100L)).thenReturn(bookBD);

        assertNotNull(service.find(100L));
        assertEquals("Лавкрафт", service.find(100L).getAuthorLastName());
    }

    @Test
    void getAllBooks() {
        when(dao.getAllBooks()).thenReturn(new ArrayList<>());
        List<BookFull> books = service.getAllBooks();
        assertNotNull(books);
    }

    @Test
    void getAllBooksByGenre() {
        when(dao.getBooksByGenre(Genre.FANTASY)).thenReturn(new ArrayList<>());
        List<BookFull> books = service.getByGenre(Genre.FANTASY);
        assertNotNull(books);
        for (BookFull book: books) {
            assertEquals(book.getGenre(), Genre.FANTASY);
        }
    }

    @Test
    void update() {
        doNothing().when(dao).updateBook(any(), anyLong());

        service.update(book, 100L);

        verify(dao, times(1)).updateBook(book, 100L);
    }

    @Test
    void delete(){
        doNothing().when(dao).deleteBook(anyLong());

        service.delete(100L);

        verify(dao, times(1)).deleteBook(100L);
    }

//    @Test
//    void countBookPage() {
//        when(dao.countBooks()).thenReturn(5);
//
//        assertEquals(service.countBookPage(5), 2);
//    }

    @Test
    void findByAuthorId() {
        books.add(bookBD);
        when(dao.findByAuthorId(100L)).thenReturn(books);

        assertEquals(service.findByAuthorId(100L), books);
        assertTrue(service.findByAuthorId(100L).contains(bookBD));
    }

    @Test
    void getNearestDateToReturn() {
        String str = "2019-12-22 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime expireDate = LocalDateTime.parse(str, formatter);
        when(dao.nearestDateToReturn(100L)).thenReturn(expireDate);

        assertEquals(service.getNearestDateToReturn(100L), expireDate);
    }
}




