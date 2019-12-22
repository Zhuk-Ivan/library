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
public class DefaultAuthorTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    public Author testAuthor(){
        Author author = new Author();
        author.setId(null);
        author.setFirstName("Ирвин");
        author.setLastName("Уэлш");
        return author;
    }

    private Author testAuthor1(){
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
        Book book = new Book(null, "Песня моряка", 412, "978-5911810808", Genre.DETECTIVE, BookStatus.FREE, 2, testAuthor1().getId());

        return book;
    }

    @Test
     void saveAuthor(){
        Author author = testAuthor();
        Long id = authorDao.createAuthor(author);
        String firstName = authorDao.findById(id).getFirstName();
        assertEquals("Ирвин", firstName);
        authorDao.deleteAuthor(id);
    }

    @Test
    void getById(){
        Long id = authorDao.createAuthor(testAuthor());
        final Author author = authorDao.findById(id);

        assertNotNull(author);
        assertEquals(author.getFirstName(), "Ирвин");
    }

    @Test
    void getByName(){
        Long id = authorDao.createAuthor(testAuthor());
        final Author author = authorDao.findByName("Ирвин", "Уэлш");

        assertNotNull(author);
        assertEquals(author.getLastName(), "Уэлш");
    }

    @Test
    void updateAuthor(){
        Long id = authorDao.createAuthor(testAuthor());
        Author newAuthor = new Author(null, "Кен", "Кизи");
        authorDao.updateAuthor(newAuthor, id);

        Author author = authorDao.findByName("Кен", "Кизи");
        assertNotNull(author);
        assertEquals(id, author.getId());
    }

   @Test
     void getAuthors(){
       Author author = testAuthor();
       Long id = authorDao.createAuthor(author);

       List<Author> authors = authorDao.getAllAuthors();
       assertNotNull(authors);

       authorDao.deleteAuthor(id);
   }


    @Test
    void deleteAuthor() {
        Author author = testAuthor();
        Long id = authorDao.createAuthor(author);

        assertNotNull(authorDao.findById(id));
        authorDao.deleteAuthor(id);
        
        assertNull(authorDao.findByName(author.getFirstName(), author.getLastName()));
    }


}


