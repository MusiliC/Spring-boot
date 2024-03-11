package com.cee.postdb.repositories;

import com.cee.postdb.TestDataUtil;

import com.cee.postdb.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //cleans the package after each test
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest = underTest;
    }
    @Test
    public  void testThatAuthorCanBeCreatedAndRecalled(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<AuthorEntity> result = underTest.findAll();

        assertThat(result).hasSize(3);
        assertThat(result).contains(author,authorA, authorB);
    }

    @Test
    public  void testThatAuthorsCanBeUpdated(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        author.setName("Updated Name");
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void testThatAuthorsCanBeDeleted(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        underTest.delete(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isEmpty();

    }

    @Test
    public  void testThatGetAuthorsWithAgeLess(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<AuthorEntity> result =    underTest.ageLessThan(18);
        assertThat(result).contains(authorA);
    }

    @Test
    public  void testThatGetAuthorsWithAgeGreat(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<AuthorEntity> result =    underTest.findAuthorWithAgeMoreThan(18);
        assertThat(result).contains(author, authorB);
    }
}
