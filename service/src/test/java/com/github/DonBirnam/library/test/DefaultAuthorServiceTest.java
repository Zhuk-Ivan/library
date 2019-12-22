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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        when(dao.findByName("Ирвин","Уэлш")).thenReturn(author);
        Author author1 = service.findByFullName("Ирвин","Уэлш");
        assertEquals(author, author1);
        when(dao.findByName("Кен","Кизи")).thenReturn(null);
        Author wrongAuthor = service.findByFullName("Кен","Кизи");
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

    @Test
    void update() {
        doNothing().when(dao).updateAuthor(any(), any());

        service.update(author, 1L);

        verify(dao, times(1)).updateAuthor(author, 1L);
    }

    @Test
    void delete(){
        doNothing().when(dao).deleteAuthor(anyLong());

        service.delete(1L);

        verify(dao, times(1)).deleteAuthor(1L);
    }

}


