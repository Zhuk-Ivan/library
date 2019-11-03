package com.github.DonBirnam.library.service.impl;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.impl.DefaultAuthorDao;
import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.service.AuthorService;

import java.util.List;

public class DefaultAuthorService implements AuthorService {

    private AuthorDao authorDao = DefaultAuthorDao.getInstance();

    private static class SingletonHolder {
        static final AuthorService HOLDER_INSTANCE = new DefaultAuthorService();
    }

    public static AuthorService getInstance(){
        return DefaultAuthorService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void save(Author author) {
        authorDao.createAuthor(author);
    }

    @Override
    public Author find(Long id) {
        Author author = authorDao.findById(id);
        if (author == null) {
            return null;
        } else {
            return author;
        }
    }

    @Override
    public Author findByLastName(String lastName) {
        Author author = authorDao.findByName(lastName);
        if (author == null) {
            return null;
        } else {
            return author;
        }
    }

    @Override
    public void update(Author author) {
        authorDao.updateAuthor(author);
    }

    @Override
    public void delete(Long id) {
        authorDao.deleteAuthor(id);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAllAuthors();
    }
}
