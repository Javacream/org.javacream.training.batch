package org.javacream.training.batch.spring.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class SimpleChunkConfiguration {

	@Value("${simple.chunk.size}")
	private int chunkSize;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired private SimpleItemReader simpleItemReader;
	@Autowired private SimpleItemProcessor simpleItemProcessor;
	@Autowired private SimpleItemWriter simpleItemWriter;
	
	@Bean
	Step simpleStep() {
		SimpleStepBuilder<String, Integer> chunk = stepBuilderFactory.get("step1").chunk(chunkSize);
		chunk.reader(simpleItemReader).processor(simpleItemProcessor).writer(simpleItemWriter);
		return chunk.build();
	}

	@Bean
	public Job job(Step simpleStep) throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(simpleStep);
		return startJob.build();
	}

}
