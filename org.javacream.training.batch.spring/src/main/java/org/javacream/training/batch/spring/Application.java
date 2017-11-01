package org.javacream.training.batch.spring;

import org.javacream.training.batch.spring.config.BatchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(BatchConfiguration.class, args);
	}
}
