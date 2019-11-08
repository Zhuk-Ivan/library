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

    private Author authorTest() {
        Author author=new Author();
        author.setId(1L);
        author.setFirstName("Лера");
        author.setLastName("Покрышкина");
        return author;
    }

    @Mock
    AuthorDao dao;

    @InjectMocks
    DefaultAuthorService service;


    @Test
    void getAllAuthors() {
        when(dao.getAllAuthors()).thenReturn(new ArrayList<Author>());
        List<Author> authors = service.getAuthors();
        assertNotNull(authors);
    }

    @Test
    public void getById() {
        Author author= authorTest();
        when(service.find(author.getId())).thenReturn(author);
        assertNotNull(service.find(author.getId()));
        assertEquals("Покрышкина",service.find(author.getId()).getLastName());
    }

    @Test
    public void deleteUser() {
        Author author= authorTest();
        when(service.find(author.getId())).thenReturn(author);
        service.delete(author.getId());
        when(service.find(author.getId())).thenReturn(null);
        assertNull(service.find(author.getId()));
    }

}


