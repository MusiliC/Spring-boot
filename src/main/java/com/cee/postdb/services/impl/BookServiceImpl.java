package com.cee.postdb.services.impl;

import com.cee.postdb.domain.entities.BookEntity;
import com.cee.postdb.repositories.BookRepository;
import com.cee.postdb.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
       return bookRepository.save(book);
    }
}
