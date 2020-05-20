package com.example.demo;

import com.example.demo.entity.Lesson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/*
W Springu architektura ma trzy warstwy:
- data access,
- service layer,
- API/Controller layer

Zaczyna się od data access
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init(){


	}
}


