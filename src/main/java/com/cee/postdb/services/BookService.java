package com.cee.postdb.services;

import com.cee.postdb.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String  isbn, BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);
}
