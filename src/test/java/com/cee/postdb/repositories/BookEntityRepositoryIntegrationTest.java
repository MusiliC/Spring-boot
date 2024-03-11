package com.cee.postdb.repositories;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Author;
import com.cee.postdb.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //cleans the package after each test
public class BookRepositoryIntegrationTest {

    private  BookRepository underTest;
    private  AuthorRepository authorRepo;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorRepo) {
        this.underTest = underTest;
        this.authorRepo = authorRepo;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecorded(){
        Author author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        Book book = TestDataUtil.createTestBook(author);

        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public  void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        Book book = TestDataUtil.createTestBook(author);

        underTest.save(book);

        Book bookA = TestDataUtil.createTestBookA(author);

        underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(author);

        underTest.save(bookB);

        Iterable<Book> result = underTest.findAll();

        assertThat(result).hasSize(3);
        assertThat(result).contains(book,bookA, bookB);
    }

    @Test
    public  void testThatAuthorsCanBeUpdated(){

        Author author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        Book book = TestDataUtil.createTestBook(author);

        underTest.save(book);

        book.setTitle("Updated Title");

        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public  void testThatBooksCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthor();
        authorRepo.save(author);

        Book book = TestDataUtil.createTestBook(author);

        underTest.save(book);


        underTest.delete(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();

    }

}
