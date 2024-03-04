package com.cee.postdb.dao.impl;

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
public class BookDaoIntegrationTest {

    private  BookDaoImp underTest;
    private  AuthorDaoImp authorDaoImp;

    @Autowired
    public BookDaoIntegrationTest(BookDaoImp underTest, AuthorDaoImp authorDaoImp) {
        this.underTest = underTest;
        this.authorDaoImp = authorDaoImp;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecorded(){
        Author author = TestDataUtil.createTestAuthor();
        authorDaoImp.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthor_id(author.getId());
        underTest.create(book);

        Optional<Book> result = underTest.find(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public  void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        authorDaoImp.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthor_id(author.getId());
        underTest.create(book);

        Book bookA = TestDataUtil.createTestBookA();
        book.setAuthor_id(author.getId());
        underTest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        book.setAuthor_id(author.getId());
        underTest.create(bookB);

        List<Book> result = underTest.findMany();

        assertThat(result).hasSize(3);
        assertThat(result).contains(book,bookA, bookB);
    }


}
