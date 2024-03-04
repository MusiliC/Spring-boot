package com.cee.postdb.dao.impl;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImpTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImp underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO AUTHORS (id, name, age) VALUES (?,?,?)"),
                eq(1L), eq("Musili"), eq(18));
    }

    @Test
    public void testThatReturnOneGeneratesCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("Select id, name, age from AUTHORS where id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImp.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatFindManyAuthorsGeneratesTheCorrectSql(){
        underTest.findMany();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImp.AuthorRowMapper>any()
        );
    }
}
