package com.github.DonBirnam.dao;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.config.DaoConfig;
import com.github.DonBirnam.library.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultBookTest {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;


    private Author testAuthor(){
        Author author = new Author(100L,"Кен","Кизи");
        authorDao.createAuthor(author);
        Author createdAuthor = authorDao.findByName(author.getFirstName(), author.getLastName());
        return createdAuthor;
    }

    private Book testBook() {
        Book book = new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, testAuthor().getId());
        return book;
    }

    @Test
    public void save(){
        Long id = bookDao.createBook(testBook());

        int pages = bookDao.findById(id).getPageCount();

        assertEquals(412, pages);
    }

    @Test
    public void findByTitle(){
        bookDao.createBook(testBook());

        BookFull book = bookDao.findByTitle("Песня моряка");

        String firstName = book.getAuthorFirstName();
        assertEquals("Кен", firstName);
    }

    @Test
    public void findById(){
        Long id = bookDao.createBook(testBook());

        BookFull book = bookDao.findById(id);

        String firstName = book.getAuthorFirstName();
        assertEquals("Кен", firstName);
    }

    @Test
    public void getBooksGenre(){
        bookDao.createBook(testBook());

        Genre genre = Genre.DETECTIVE;
        List<BookFull> books = bookDao.getBooksByGenre(genre);

        BookFull book = books.iterator().next();
        assertEquals(Genre.DETECTIVE, book.getGenre());
    }

    @Test
    public void updateStatus(){
        Long id = bookDao.createBook(testBook());

        bookDao.updateBookStatus(BookStatus.OCCUPIED, id);

        BookFull book = bookDao.findById(id);
        assertEquals(book.getStatus(), BookStatus.OCCUPIED);
    }

    @Test
    public void updateBook(){
        Long id = bookDao.createBook(testBook());
        Book newBook = new Book(null, "Американский психопат",350,"978-5911810808", Genre.DETECTIVE,BookStatus.OCCUPIED, 5, null);

        bookDao.updateBook(newBook, id);

        BookFull book = bookDao.findById(id);
        assertEquals(book.getTitle(), "Американский психопат");
        assertEquals(book.getInStock(), 5);
    }

    @Test
    public void getBooks(){
        bookDao.createBook(testBook());

        List<BookFull> books = bookDao.getAllBooks();

        assertNotNull(books);
    }


    @Test
    public void deleteBook(){
        Long id = bookDao.createBook(testBook());

        BookFull book = bookDao.findById(id);
        assertNotNull(bookDao.findById(book.getId()));

        bookDao.deleteBook(book.getId());
        assertNull(bookDao.findByTitle("Песня моряка"));
    }
}
