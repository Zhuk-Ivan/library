package com.github.DonBirnam.library.dao;

import com.github.DonBirnam.library.model.Author;

import java.util.List;

public interface AuthorDao {

    Long createAuthor(Author author);

    Author findById(Long id);

    Author findByName(String firstName, String lastName);

    void updateAuthor(Author author, Long id);

    void deleteAuthor(Long id);

    List <Author> getAllAuthors();

}
