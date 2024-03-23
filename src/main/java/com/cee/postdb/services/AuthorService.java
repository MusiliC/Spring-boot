package com.cee.postdb.services;


import com.cee.postdb.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity saveAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long authorId);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long authorId, AuthorEntity authorEntity);

    void delete(Long id);
}
