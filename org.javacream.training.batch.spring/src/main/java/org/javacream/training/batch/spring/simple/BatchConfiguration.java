package org.javacream.training.batch.spring.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Bean Step taskletStep(SimpleTasklet simpleTasklet) {
		TaskletStep step = stepBuilderFactory.get("step1").tasklet(simpleTasklet).build();
		return step;
	}
	
	@Bean
	public Job job(Step taskletStep) throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(taskletStep);
		return startJob.build();
	}
}
