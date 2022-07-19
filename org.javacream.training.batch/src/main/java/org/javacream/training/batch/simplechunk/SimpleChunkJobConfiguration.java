package org.javacream.training.batch.simplechunk;

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
public class SimpleChunkJobConfiguration {

	@Autowired SimpleItemProcessor processor;
	@Autowired SimpleItemReader reader;
	@Autowired SimpleItemWriter writer;

	
	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;

	@Bean public Step step() {
		return stepBuilderFactory.get("step1").<String, Integer>chunk(3).reader(reader).processor(processor).writer(writer).build();
	}
	@Bean @Qualifier("simplechunk") public Job simpleJunk() {
		return jobBuilderFactory.get("chunk-job").start(step()).build();
	}
}
