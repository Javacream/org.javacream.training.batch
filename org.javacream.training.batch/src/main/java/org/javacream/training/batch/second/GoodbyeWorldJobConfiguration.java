package org.javacream.training.batch.second;

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
public class GoodbyeWorldJobConfiguration {

	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;
	
	
	@Bean public Step goodbyeWorldStep() {
		return stepBuilderFactory.get("gb-world-step").tasklet(new SecondTasklet()).build();
	}
	
	//Spring Boot: Dieser Job wird automatisch (!) gestartet und bekommt als Parametrisierung nichts
	@Bean @Qualifier("goodbyeWorld") public Job goodbyeWorldJob() {
		return jobBuilderFactory.get("second-job").start(goodbyeWorldStep()).build();
	}
}
