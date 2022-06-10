package org.javacream.training.batch.sequence;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SequenceFlowConfiguration {
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1(Step1Tasklet tasklet) {
		return stepBuilderFactory.get("step1").tasklet(tasklet).build();
	}
	@Bean
	public Step step2(Step2Tasklet tasklet) {
		return stepBuilderFactory.get("step2").tasklet(tasklet).build();
	}
	@Bean
	public Step step3(Step3Tasklet tasklet) {
		return stepBuilderFactory.get("step3").tasklet(tasklet).build();
	}

	@Bean
	@Qualifier("simpleSequence")
	public Job simpleSequenceJob(Step1Tasklet tasklet1, Step2Tasklet tasklet2, Step3Tasklet tasklet3) {
		return jobBuilderFactory.get("simple-sequence-job").start(step1(tasklet1)).next(step2(tasklet2)).next(step3(tasklet3)).build();
	}
}
