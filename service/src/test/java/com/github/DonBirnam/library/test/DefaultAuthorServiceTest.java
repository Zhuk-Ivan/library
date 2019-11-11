package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.service.impl.DefaultAuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultAuthorServiceTest {


    private Author author = new Author(1L, "Ирвин", "Уэлш");
    @Mock
    AuthorDao dao;

    @InjectMocks
    DefaultAuthorService service;

    @Test
    public void getById() {
        when(dao.findById(1L)).thenReturn(author);

        assertNotNull(service.find(1L));
    }


    @Test
    public void getByLastName() {
        when(dao.findByName("Уэлш")).thenReturn(author);
        Author author1 = service.findByLastName("Уэлш");
        assertEquals(author, author1);
        when(dao.findByName("Кизи")).thenReturn(null);
        Author wrongAuthor = service.findByLastName("Кизи");
        assertNull(wrongAuthor);
    }

    @Test
    void getAllAuthors() {
        when(dao.getAllAuthors()).thenReturn(new ArrayList<>());
        List<Author> authors = service.getAuthors();
        assertNotNull(authors);
    }

    @Test
    void saveAuthor(){
        when(dao.createAuthor(author)).thenReturn(100L);

        Long id = service.save(author);

        assertEquals(id, 100L);

    }

}


