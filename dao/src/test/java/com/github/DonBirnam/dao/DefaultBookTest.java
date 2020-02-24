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
        Author author = new Author(null,"Кен","Кизи");
        Long id = authorDao.createAuthor(author);
        Author createdAuthor = authorDao.findById(id);
        return createdAuthor;
    }

    private Author testAutor2(){
        Author author = new Author(null,"Ирвин","Уэлш");
        Long id = authorDao.createAuthor(author);
        Author createdAuthor = authorDao.findById(id);
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

    @Test
    public void findByAuthorId(){
        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
        Long anotherAuthorId = authorDao.createAuthor(new Author(null,"Ирвин","Уэлш"));

        Long id = bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));

        List<BookFull> authorBooks = bookDao.findByAuthorId(authorId);
        BookFull book =  bookDao.findById(id);
        assertNotNull(authorBooks);
        assertEquals(authorBooks.get(0).getTitle(), book.getTitle());


        List<BookFull> emptyBooks = bookDao.findByAuthorId(anotherAuthorId);
        assertEquals(emptyBooks.size(), 0);
    }

//    @Test
//    public void countBooks(){
//        Long authorId = authorDao.createAuthor(new Author(null,"Кен","Кизи"));
//        bookDao.createBook(new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, authorId));
//        bookDao.createBook(new Book(null, "Пролетая над гнездом кукушки", 360, "978-5911810808", Genre.DRAMA, BookStatus.FREE, 3, authorId));
//        int booksSize = bookDao.countBooks();
//
//        assertEquals(booksSize, 2);
//    }


}

