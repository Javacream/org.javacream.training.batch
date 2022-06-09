package org.javacream.training.batch.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {
	
	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;
	
	
	@Bean public Step helloWorldPrintlnStep() {
		return stepBuilderFactory.get("hello-world-step").tasklet(new SimpleTasklet()).build();
	}
	@Bean public Job helloWorldJob() {
		return jobBuilderFactory.get("hello-world-job").start(helloWorldPrintlnStep()).build();
	}

}
