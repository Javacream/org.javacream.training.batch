package org.javacream.training.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//String[] params = {"name=world", "timestamp=" + System.currentTimeMillis()}; 
		SpringApplication.run(Application.class, args);
	}

}
