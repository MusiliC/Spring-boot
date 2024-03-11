package com.cee.postdb.services.impl;

import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.repositories.AuthorRepository;
import com.cee.postdb.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);

    }
}
