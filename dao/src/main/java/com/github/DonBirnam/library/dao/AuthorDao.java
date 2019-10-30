package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Author;

import java.util.List;

public interface AuthorDao {

    void createAuthor(Author author);

    Author findById(Long id);

    Author findByName(String lastName);

    void updateAuthor(Author author);

    void deleteAuthor(Long id);

    List <Author> getAllAuthors();

}
