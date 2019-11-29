//package com.github.DonBirnam.dao;
//
//import com.github.DonBirnam.library.dao.AuthorDao;
//import com.github.DonBirnam.library.dao.BookDao;
//import com.github.DonBirnam.library.dao.impl.DefaultAuthorDao;
//import com.github.DonBirnam.library.dao.impl.DefaultBookDao;
//import com.github.DonBirnam.library.model.Author;
//import com.github.DonBirnam.library.model.Book;
//import com.github.DonBirnam.library.model.BookStatus;
//import com.github.DonBirnam.library.model.Genre;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DefaultBookTest {
//    BookDao bookDao = DefaultBookDao.getInstance();
//    AuthorDao authorDao = DefaultAuthorDao.getInstance();
//
//    private Author testAuthor(){
//        Author author = new Author(null,"Кен","Кизи");
//        authorDao.createAuthor(author);
//        Author createdAuthor = authorDao.findByName(author.getLastName());
//        return createdAuthor;
//    }
//
//    private Book testBook() {
//        Book book = new Book();
//        book.setId(null);
//        book.setTitle("Песня моряка");
//        book.setAuthor(testAuthor());
//        book.setIsbn("978-5911810808");
//        book.setGenre(Genre.DRAMA);
//        book.setPageCount(412);
//        book.setInStock(2);
//        book.setStatus(BookStatus.FREE);
//        return book;
//    }
//
//    @Test
//    public void save(){
//        Book book = testBook();
//        bookDao.createBook(book);
//        int pages = bookDao.findByTitle(book.getTitle()).getPageCount();
//        assertEquals(412, pages);
//    }
//
//    @Test
//    public void findByTitle(){
//        Book book = bookDao.findByTitle("Песня моряка");
//        String firstName = book.getAuthor().getFirstName();
//        assertEquals("Кен", firstName);
//    }
//
//    @Test
//    public void getBooksGenre(){
//        Genre genre = Genre.DETECTIVE;
//        List<Book> books = bookDao.getBooksByGenre(genre);
//        Book book = books.iterator().next();
//        assertEquals(Genre.DETECTIVE, book.getGenre());
//    }
//
//    @Test
//    public void getBooks(){
//        List<Book> books = bookDao.getAllBooks();
//        assertNotNull(books);
//    }
//
//
//    @Test
//    public void deleteBook(){
//        Book book = bookDao.findByTitle("Песня моряка");
//        Long id = book.getAuthor().getId();
//        assertNotNull(bookDao.findById(book.getId()));
//        bookDao.deleteBook(book.getId());
//        assertNull(bookDao.findByTitle("Песня моряка"));
//
//        authorDao.deleteAuthor(id);
//    }
//}
