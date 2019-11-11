package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.impl.DefaultAuthorDao;
import com.github.DonBirnam.library.model.Author;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthorTest {

    private AuthorDao authorDao = DefaultAuthorDao.getInstance();

    public Author testAuthor(){
        Author author = new Author();
        author.setId(null);
        author.setFirstName("Ирвин");
        author.setLastName("Уэлш");
        return author;
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
     void getAuthors(){
       Author author = testAuthor();
       Long id = authorDao.createAuthor(author);

       List<Author> authors = DefaultAuthorDao.getInstance().getAllAuthors();
       assertNotNull(authors);

       authorDao.deleteAuthor(id);
   }


    @Test
    void deleteAuthor() {
        Author author = testAuthor();
        Long id = authorDao.createAuthor(author);

        assertNotNull(authorDao.findById(id));
        authorDao.deleteAuthor(id);
        
        assertNull(authorDao.findByName(author.getLastName()));
    }


    @AfterAll
     static void cleanUp() {
        EntityManager em = HibernateUtil.getSession();
        if (em != null) {
            em.close();
        }
    }

}


