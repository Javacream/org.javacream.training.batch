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
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution,
					org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
				System.out.println("step 1 writes property forStep1 "
						+ chunkContext.getStepContext().getJobParameters().get("forStep1"));
				chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("fromStep1", "greetings from step 1!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution,
					org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
				System.out.println("step 2 writes property forStep2 "
						+ chunkContext.getStepContext().getJobParameters().get("forStep2"));
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution,
					org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
				System.out.println("step 3 writes property forStep3 "
						+ chunkContext.getStepContext().getJobParameters().get("forStep3") + " and greetings " + chunkContext.getStepContext().getJobExecutionContext().get("fromStep1"));
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	@Qualifier("simpleSequence")
	public Job simpleSequenceJob() {
		return jobBuilderFactory.get("simple-sequence-job").start(step1()).next(step2()).next(step3()).build();
	}
}
