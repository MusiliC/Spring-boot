package com.cee.postdb.dao;

import com.cee.postdb.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);

    List<Book> findMany();

    void update(String isbn, Book book);

    void deleteBook(String isbn);
}
