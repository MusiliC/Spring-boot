package com.cee.postdb.repositories;

import com.cee.postdb.TestDataUtil;

import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //cleans the package after each test
public class BookEntityRepositoryIntegrationTest {

    private  BookRepository underTest;
    private  AuthorRepository authorRepo;

    @Autowired
    public BookEntityRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorRepo) {
        this.underTest = underTest;
        this.authorRepo = authorRepo;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecorded(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public  void testThatMultipleBooksCanBeCreatedAndRecalled(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        underTest.save(bookEntity);

        BookEntity bookEntityA = TestDataUtil.createTestBookA(author);

        underTest.save(bookEntityA);

        BookEntity bookEntityB = TestDataUtil.createTestBookB(author);

        underTest.save(bookEntityB);

        Iterable<BookEntity> result = underTest.findAll();

        assertThat(result).hasSize(3);
        assertThat(result).contains(bookEntity, bookEntityA, bookEntityB);
    }

    @Test
    public  void testThatAuthorsCanBeUpdated(){

        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        underTest.save(bookEntity);

        bookEntity.setTitle("Updated Title");

        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public  void testThatBooksCanBeDeleted(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        underTest.save(bookEntity);


        underTest.delete(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isEmpty();

    }

}
