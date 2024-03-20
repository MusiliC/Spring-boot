package com.cee.postdb.controllers;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.dto.BookDto;
import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.domain.entities.BookEntity;
import com.cee.postdb.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    private MockMvc mockMvc;
    private BookService bookService;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public  void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }


    @Test
    public  void testThatCreateBookSuccessfullyReturnsCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        );
    }

    @Test
    public  void testThatListBooksReturnsHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public  void testThatListBooksReturnsListOfBooks() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBook(null);
        bookService.createBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("2020-010-100")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow against US")

        );
    }

        // @Test
        // public void testThatGetBookReturnsStatus201() throws Exception {

        //         BookEntity bookEntity = TestDataUtil.createTestBook(null);
             
        //         bookService.createBook(null, bookEntity);

        //         mockMvc.perform(
        //                         MockMvcRequestBuilders.get("/books/1")
        //                                         .contentType(MediaType.APPLICATION_JSON)

        //         ).andExpect(
        //                         MockMvcResultMatchers.status().isOk());
        // }

        // @Test
        // public void testThatListBookReturns404WhenNoBook() throws Exception {

        //         mockMvc.perform(
        //                         MockMvcRequestBuilders.get("/books/99")
        //                                         .contentType(MediaType.APPLICATION_JSON)

        //         ).andExpect(
        //                         MockMvcResultMatchers.status().isNotFound());
        // }
}
