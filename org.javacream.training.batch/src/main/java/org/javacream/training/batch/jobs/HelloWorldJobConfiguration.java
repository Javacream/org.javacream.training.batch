package org.javacream.training.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step helloWorldStep() {
//		HelloWorldTasklet hwt = new HelloWorldTasklet();
//		Step helloWorldStep = stepBuilderFactory.get("hello-world").tasklet(hwt).build();
		Step helloWorldStep = stepBuilderFactory.get("hello-world")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Hello World");
					return RepeatStatus.FINISHED;
				}).build();
		return helloWorldStep;

	}

	@Bean
	public Job helloWorldJob() {
		return jobBuilderFactory.get("hello-world-job").start(helloWorldStep()).build();
	}

//	public class HelloWorldTasklet implements Tasklet {
//
//		@Override
//		public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//			System.out.println("Hello World!");
//			return RepeatStatus.FINISHED;
//		}
//
//	}
}
