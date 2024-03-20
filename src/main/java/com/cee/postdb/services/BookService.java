package com.cee.postdb.services;

import com.cee.postdb.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String  isbn, BookEntity book);

    List<BookEntity> findAll();
}
