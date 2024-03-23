package com.cee.postdb;


import com.cee.postdb.domain.dto.AuthorDto;
import com.cee.postdb.domain.dto.BookDto;
import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Musili")
                .age(28)
                .build();
    }

    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .id(1L)
                .name("Musili")
                .age(28)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Brian")
                .age(16)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Mayani")
                .age(23)
                .build();
    }


    public static BookEntity createTestBook(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("2020-010-100")
                .title("The Shadow against US")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookA(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("2020-010-200")
                .title("Atomic Habits")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoA(AuthorDto author) {
        return BookDto.builder()
                .isbn("2020-010-200")
                .title("Atomic Habits")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookB(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("2020-010-300")
                .title("The Superior way of a man")
                .author(author)
                .build();
    }



 
}
