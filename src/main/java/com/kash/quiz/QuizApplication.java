package com.kash.quiz;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	/*----Now We Can Uses ModelMapper In Our Project----*/
	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
}
