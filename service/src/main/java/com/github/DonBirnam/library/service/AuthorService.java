package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);

    Author find(Long id);

    Author findByLastName(String lastName);

    void update(Author author);

    void delete(Long id);

    List <Author> getAuthors();

}
