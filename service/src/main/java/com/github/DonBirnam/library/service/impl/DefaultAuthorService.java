package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorDao authorDao;

    public DefaultAuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    @Transactional
    public Long save(Author author) {
        Long authorId = authorDao.createAuthor(author);
        return authorId;
    }

    @Override
    @Transactional
    public Author find(Long id) {
        Author author = authorDao.findById(id);
        if (author == null) {
            return null;
        } else {
            return author;
        }
    }

    @Override
    @Transactional
    public Author findByFullName(String firstName, String lastName) {
        Author author = authorDao.findByName(firstName, lastName);
        if (author == null) {
            return null;
        } else {
            return author;
        }
    }

    @Override
    @Transactional
    public void update(Author author, Long id) {
        authorDao.updateAuthor(author, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        authorDao.deleteAuthor(id);
    }

    @Override
    @Transactional
    public List<Author> getAuthors() {
        return authorDao.getAllAuthors();
    }
}
