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
	
	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;
	
	
	@Bean public Step chunkStep(SimpleReader reader, SimpleWriter writer, SimpleProcessor processor) {
		return stepBuilderFactory.get("chunk").<String, Integer>chunk(2).reader(reader).processor(processor).writer(writer).allowStartIfComplete(true).build();
	}
	@Bean @Qualifier("simpleChunkJob") public Job simpleChunkJob(SimpleReader reader, SimpleWriter writer, SimpleProcessor processor) {
		return jobBuilderFactory.get("chunkJob").start(chunkStep(reader, writer, processor)). build();
	}
}
