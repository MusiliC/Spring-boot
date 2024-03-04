package com.cee.postdb.dao.impl;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.Author;
import com.cee.postdb.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
}
