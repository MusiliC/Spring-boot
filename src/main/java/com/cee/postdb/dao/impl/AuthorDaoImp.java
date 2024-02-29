package com.cee.postdb.dao.impl;

import com.cee.postdb.dao.AuthorDao;
import com.cee.postdb.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
