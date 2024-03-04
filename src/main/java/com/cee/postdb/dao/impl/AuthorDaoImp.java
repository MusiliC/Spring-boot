package com.cee.postdb.dao.impl;


import com.cee.postdb.dao.AuthorDao;
import com.cee.postdb.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImp implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public  AuthorDaoImp(final  JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
    jdbcTemplate.update("INSERT INTO AUTHORS (id, name, age) VALUES (?,?,?)",
            author.getId(),
            author.getName(),
            author.getAge()
    );
    }

    @Override
    public Optional<Author> findOne(Long authorId) {
     List<Author> results = jdbcTemplate.query("Select id, name, age from AUTHORS where id = ? LIMIT 1",
                new AuthorRowMapper(), authorId
        );

    return results.stream().findFirst();
    }

    public  static  class AuthorRowMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
