package com.cee.postdb.controllers;


import com.cee.postdb.domain.dto.AuthorDto;
import com.cee.postdb.domain.entities.AuthorEntity;
import com.cee.postdb.mappers.Mapper;
import com.cee.postdb.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto){
       AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
       AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
       return  authorMapper.mapTo(savedAuthorEntity);

    }

}
