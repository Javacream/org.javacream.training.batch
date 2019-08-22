package org.javacream.training.batch.spring.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@ComponentScan
public class SimpleJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired SimpleTasklet simpleTasklet;

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").tasklet(simpleTasklet).build();
	}
	
	@Bean
	public Job job() throws Exception {
		JobBuilder jobStart = jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startStep = jobStart.start(step());
		return startStep.build();
	}
}
