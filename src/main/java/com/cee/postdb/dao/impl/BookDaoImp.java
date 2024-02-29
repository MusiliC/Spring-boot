package com.cee.postdb.dao.impl;

import com.cee.postdb.dao.BookDao;
import com.cee.postdb.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImp implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public  BookDaoImp(final  JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (isbn, title, authorId) VALUES (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }
}
