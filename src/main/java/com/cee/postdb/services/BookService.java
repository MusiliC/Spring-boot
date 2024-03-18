package com.cee.postdb.services;

import com.cee.postdb.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String  isbn, BookEntity book);
}
