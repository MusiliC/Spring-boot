package com.cee.postdb.dao.impl;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoIntegrationTests {

    private AuthorDaoImp underTest;

    @Autowired
    public AuthorDaoIntegrationTests(AuthorDaoImp underTest){
        this.underTest = underTest;
    }
    @Test
    public  void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
