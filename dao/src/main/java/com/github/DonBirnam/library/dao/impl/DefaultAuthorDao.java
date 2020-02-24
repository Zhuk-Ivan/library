package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.converter.AuthorConverter;
import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import com.github.DonBirnam.library.dao.repository.AuthorRepository;
import com.github.DonBirnam.library.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DefaultAuthorDao implements AuthorDao {

    private final AuthorRepository authorRepository;

    public DefaultAuthorDao(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);


    @Override
    public Long createAuthor(Author author) {
        AuthorEntity authorEntity = AuthorConverter.toEntity(author);
        authorRepository.save(authorEntity);
        return authorEntity.getId();
    }

    @Override
    public Author findById(Long id) {
        final AuthorEntity authorEntity = authorRepository.getOne(id);
        return AuthorConverter.fromEntity(authorEntity);
    }


    @Override
    public Author findByName(String firstName, String lastName) {
        final AuthorEntity authorEntity = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        return AuthorConverter.fromEntity(authorEntity);
    }



    @Override
    public void updateAuthor(Author author, Long id) {
        authorRepository.update(id,author.getFirstName(),author.getLastName());
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        List<AuthorEntity> authors = authorRepository.findAll();
        return AuthorConverter.fromEntityList(authors);
    }
}
