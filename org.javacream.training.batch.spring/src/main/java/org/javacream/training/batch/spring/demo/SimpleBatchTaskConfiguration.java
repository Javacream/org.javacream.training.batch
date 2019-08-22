package org.javacream.training.batch.spring.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@EnableBatchProcessing
@Configuration
public class SimpleBatchTaskConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleBatchTask simpleTasklet;

	@Bean
	TaskletStep simpleStep() {
		TaskletStep step = stepBuilderFactory.get("step").tasklet(simpleTasklet).build();
		return step;
	}

	@Bean
	public Job job() throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(simpleStep());
		return startJob.build();
	}

}
