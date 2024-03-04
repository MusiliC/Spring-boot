package com.cee.postdb.dao.impl;


import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Author;
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

    @Test
    public  void testThatFindManyBooksGeneratesTheCorrectSql(){
        underTest.findMany();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImp.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateBookGeneratesTheCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        underTest.update(book.getIsbn(), book);

        verify(jdbcTemplate).update(
                "UPDATE books set isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "2020-010-100", "The Shadow against US", 1L,"2020-010-100"
        );
    }

    @Test
    public  void testThatDeleteBookGeneratesSql(){
        underTest.deleteBook("2020-010-100");
        verify(jdbcTemplate).update("DELETE FROM books where isbn = ?", "2020-010-100");
    }
}
