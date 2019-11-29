package com.github.DonBirnam.library.service;

import com.github.DonBirnam.library.model.Author;

import java.util.List;

public interface AuthorService {

    Long save(Author author);

    Author find(Long id);

    Author findByFullName(String firstName, String lastName);

    void update(Author author);

    void delete(Long id);

    List <Author> getAuthors();

}
