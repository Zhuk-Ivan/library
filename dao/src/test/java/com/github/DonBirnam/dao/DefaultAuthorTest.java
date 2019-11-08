package com.github.DonBirnam.dao;


import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.impl.DefaultAuthorDao;
import com.github.DonBirnam.library.model.Author;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthorTest {

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
        Long id = DefaultAuthorDao.getInstance().createAuthor(author);
        String firstName = DefaultAuthorDao.getInstance().findById(id).getFirstName();
        assertEquals("Ирвин", firstName);
    }


   @Test
     void getAuthors(){
       List<Author> authors = DefaultAuthorDao.getInstance().getAllAuthors();
       assertNotNull(authors);
   }


    @Test
    void deleteAuthor() {
        Author author = DefaultAuthorDao.getInstance().findByName("Уэлш");
        assertNotNull(DefaultAuthorDao.getInstance().findById(author.getId()));
        DefaultAuthorDao.getInstance().deleteAuthor(author.getId());
        assertNull(DefaultAuthorDao.getInstance().findByName(author.getLastName()));
    }


    @AfterAll
     static void cleanUp() {
        EntityManager em = HibernateUtil.getSession();
        if (em != null) {
            em.close();
        }
    }

}


