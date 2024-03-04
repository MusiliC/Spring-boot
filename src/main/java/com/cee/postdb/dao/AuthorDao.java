package com.cee.postdb.dao;

import java.util.List;
import java.util.Optional;

import com.cee.postdb.domain.Author;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(Long L);

    List<Author> findMany();
}
