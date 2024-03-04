package com.cee.postdb.dao.impl;


import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImpTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImp underTest;

    @Test
    public  void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"),
                eq("2020-010-100"), eq("The Shadow against US"), eq(1L)
        );
    }

    @Test
    public  void testThatFindOneBookGeneratesCorrectSql(){
        underTest.find("2020-010-100");
        verify(jdbcTemplate).query(
               eq( "SELECT isbn, title, author_id from books where isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImp.BookRowMapper>any(),
                eq("2020-010-100"));

    }
}
