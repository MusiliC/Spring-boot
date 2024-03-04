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


    public static Book createTestBook() {
        return Book.builder()
                .isbn("2020-010-100")
                .title("The Shadow against US")
                .author_id(1L)
                .build();
    }
}
