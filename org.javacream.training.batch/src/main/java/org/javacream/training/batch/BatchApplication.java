package org.javacream.training.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		String[] params = {"name=foo"};
		SpringApplication.run(BatchApplication.class, params);
	}

}
