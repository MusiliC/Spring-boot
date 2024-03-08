package com.cee.postdb;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class PostdbApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PostdbApplication.class, args);
	}



}
