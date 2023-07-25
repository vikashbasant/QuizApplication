package com.kash.quiz;

import com.kash.quiz.helper.ApplicationRunnerForUserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {

	@Autowired
	private ApplicationRunnerForUserRole applicationRunnerForUserRole;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	/*----Now We Can Uses ModelMapper In Our Project----*/
	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		this.applicationRunnerForUserRole.createRoles();
	}
}
