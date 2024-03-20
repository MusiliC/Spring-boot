package com.cee.postdb.services.impl;

import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.repositories.AuthorRepository;
import com.cee.postdb.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream( authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long authorId) {
      return authorRepository.findById(authorId);
    }
}
