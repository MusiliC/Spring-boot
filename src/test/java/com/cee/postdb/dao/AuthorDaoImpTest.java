package com.cee.postdb.dao;

import com.cee.postdb.dao.impl.AuthorDaoImp;
import com.cee.postdb.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImpTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
   private AuthorDaoImp underTest;

    @Test
    public  void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = Author.builder()
                .id(1L)
                .name("Musili")
                .age(18)
                .build();
        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO AUTHORS (id, name, age) VALUES (?,?,?)"),
                eq(1L), eq("Musili"), eq(18)
        );
    }
}
