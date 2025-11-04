package org.javacream.training.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"jobs/multiStepJob.xml", "jobs/chunkFileXmlJob.xml", "jobs/jsonToFileXmlJob.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
