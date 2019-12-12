package com.github.DonBirnam.library.dao.converter;


import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import com.github.DonBirnam.library.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter {
    public static AuthorEntity toEntity(Author author) {
        if (author == null) {
            return null;
        }
        final AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setFirstName(author.getFirstName());
        authorEntity.setLastName(author.getLastName());
        return authorEntity;
    }

    public static Author fromEntity(AuthorEntity authorEntity) {
        if (authorEntity == null) {
            return null;
        }
        return new Author(
                authorEntity.getId(),
                authorEntity.getFirstName(),
                authorEntity.getLastName());
    }

    public static List<Author> fromEntityList(List<AuthorEntity> authorEntities) {
        if (authorEntities == null){
            return null;
        }
        final List<Author> authors = new ArrayList<>();
        for (AuthorEntity authorEntity : authorEntities) {
            authors.add(fromEntity(authorEntity));
        }
        return authors;
    }
}
