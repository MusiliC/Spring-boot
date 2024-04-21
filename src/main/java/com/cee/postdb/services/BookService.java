package com.cee.postdb.services;

import com.cee.postdb.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookEntity createUpdateBook(String  isbn, BookEntity book);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);


    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
