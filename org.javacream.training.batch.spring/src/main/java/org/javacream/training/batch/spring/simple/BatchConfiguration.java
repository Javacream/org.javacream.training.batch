package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@PostConstruct public void init() {
		System.out.println("******************");
	}
}
