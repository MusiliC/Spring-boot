package com.cee.postdb.controllers;

import com.cee.postdb.domain.dto.AuthorDto;
import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.mappers.Mapper;
import com.cee.postdb.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.saveAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);

    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authorEntities = authorService.findAll();
        return authorEntities.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("authorId") Long authorId) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(authorId);
        return foundAuthor.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/authors/{authorId}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("authorId") Long authorId,
            @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(authorId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(authorId);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);

        AuthorEntity savedAuthor = authorService.saveAuthor(authorEntity);

        return new ResponseEntity<>(authorMapper.mapTo(savedAuthor), HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{authorId}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(@PathVariable("authorId") Long authorId,
            @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(authorId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = authorService.partialUpdate(authorId, authorEntity);
        return new ResponseEntity<>(
                authorMapper.mapTo(updatedAuthor),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
