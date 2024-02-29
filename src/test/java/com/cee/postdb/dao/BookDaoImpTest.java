package com.cee.postdb.dao;


import com.cee.postdb.dao.impl.BookDaoImp;
import com.cee.postdb.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        Book book = Book.builder()
                .isbn("2020-010-100")
                .title("The Shadow against US")
                .authorId(1L)
                .build();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, authorId) VALUES (?,?,?)"),
                eq("2020-010-100"), eq("The Shadow against US"), eq(1L)
        );
    }
}
