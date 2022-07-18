package org.javacream.training.batch.sequence;

import org.javacream.training.batch.first.FirstTasklet;
import org.javacream.training.batch.second.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceJobConfiguration {
	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;

	@Bean public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				chunkContext.getStepContext().getJobParameters().get("forStep1");
				chunkContext.getStepContext().getStepExecutionContext().put("this", "that");
				System.out.println("STEP 1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean public Step step2() {
		return stepBuilderFactory.get("step2").tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
				System.out.println("STEP 1");
				return RepeatStatus.FINISHED;
			}
		).build();
	}
	@Bean public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new FirstTasklet()).build();
	}

	@Bean @Qualifier("simpleSequence") public Job simpleSequenceJob() {
		return jobBuilderFactory.get("second-job").start(step1()).next(step2()).next(step3()).build();
	}

	
	
}
