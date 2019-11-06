package com.github.DonBirnam.library.dao.converter;


import com.github.DonBirnam.library.dao.entity.AuthorEntity;
import com.github.DonBirnam.library.model.Author;

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
}
