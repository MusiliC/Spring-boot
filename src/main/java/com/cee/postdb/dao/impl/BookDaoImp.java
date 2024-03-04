package com.cee.postdb.dao.impl;

import com.cee.postdb.dao.BookDao;
import com.cee.postdb.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImp implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public  BookDaoImp(final  JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor_id()
        );
    }

    @Override
    public Optional<Book> find(String isbn) {
       List<Book> results = jdbcTemplate.query(
               "SELECT isbn, title, author_id from books where isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        return results.stream().findFirst();
    }

    public  static  class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
          return   Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id(rs.getLong("author_id"))
                    .build();

        }
    }
}
