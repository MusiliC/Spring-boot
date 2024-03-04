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
                .id(4L)
                .name("Mayani")
                .age(23)
                .build();
    }


    public static Book createTestBook() {
        return Book.builder()
                .isbn("2020-010-100")
                .title("The Shadow against US")
                .author_id(1L)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("2020-010-200")
                .title("Atomic Habits")
                .author_id(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("2020-010-300")
                .title("The Superior way of a man")
                .author_id(1L)
                .build();
    }
}
