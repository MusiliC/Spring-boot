package com.cee.postdb.controllers;

import com.cee.postdb.TestDataUtil;
import com.cee.postdb.domain.dto.AuthorDto;
import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
class AuthorControllerIntegrationTest {

        private MockMvc mockMvc;
        private AuthorService authorService;

        private ObjectMapper objectMapper;

        @Autowired
        public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService authorService) {
                this.mockMvc = mockMvc;
                this.authorService = authorService;
                this.objectMapper = new ObjectMapper();
        }

        @Test
        public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
                AuthorEntity author = TestDataUtil.createTestAuthor();
                author.setId(null);

                String authorJson = objectMapper.writeValueAsString(author);
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());
        }

        @Test
        public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
                AuthorEntity author = TestDataUtil.createTestAuthor();
                author.setId(null);

                String authorJson = objectMapper.writeValueAsString(author);
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Musili"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(28));
        }

        @Test
        public void testThatListAuthorsReturnsStatus201() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(
                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatListAuthorsReturnsListOfAuthors() throws Exception {

                AuthorEntity author = TestDataUtil.createTestAuthor();
                author.setId(null);
                authorService.saveAuthor(author);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(
                                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()).andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].name").value("Musili"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].age").value(28));
        }

        @Test
        public void testThatListAuthorsReturnsStatus201WhenAuthorExists() throws Exception {

                AuthorEntity author = TestDataUtil.createTestAuthor();
                author.setId(null);
                authorService.saveAuthor(author);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/authors/1")
                                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(
                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatListAuthorsReturns404WhenNoAuthor() throws Exception {

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/authors/99")
                                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(
                                MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
                AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDto();
                String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/authors/99")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorDtoJson))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatFullUpdateAuthorReturnsHttpStatus4200WhenAuthorExists() throws Exception {
                AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthor();
                AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

                AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDto();
                String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorDtoJson))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
                AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthor();
                AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

                AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
                authorDto.setId(savedAuthor.getId());

                String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorDtoUpdateJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge()));
        }

}