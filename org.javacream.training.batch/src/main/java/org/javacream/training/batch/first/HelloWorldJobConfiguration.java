package org.javacream.training.batch.first;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {

	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;
	
	
	@Bean public Step helloWorldStep() {
		return stepBuilderFactory.get("hello-world-step").tasklet(new FirstTasklet()).build();
	}
	
	//Spring Boot: Dieser Job wird automatisch (!) gestartet und bekommt als Parametrisierung nichts
	@Bean @Qualifier("helloWorld") public Job helloWorldJob() {
		return jobBuilderFactory.get("hello-world-job").start(helloWorldStep()).build();
	}
}
