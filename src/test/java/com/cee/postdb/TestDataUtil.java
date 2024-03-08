package com.cee.postdb;

import com.cee.postdb.domain.Author;
import com.cee.postdb.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Musili")
                .age(18)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(2L)
                .name("Brian")
                .age(16)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(3L)
                .name("Mayani")
                .age(23)
                .build();
    }


    public static Book createTestBook(Author author) {
        return Book.builder()
                .isbn("2020-010-100")
                .title("The Shadow against US")
                .author(author)
                .build();
    }

    public static Book createTestBookA(Author author) {
        return Book.builder()
                .isbn("2020-010-200")
                .title("Atomic Habits")
                .author(author)
                .build();
    }

    public static Book createTestBookB(Author author) {
        return Book.builder()
                .isbn("2020-010-300")
                .title("The Superior way of a man")
                .author(author)
                .build();
    }
}
