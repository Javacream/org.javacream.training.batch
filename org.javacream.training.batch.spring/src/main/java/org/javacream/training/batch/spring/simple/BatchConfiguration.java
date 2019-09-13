package org.javacream.training.batch.spring.simple;

import org.javacream.training.batch.spring.simple.simplestep.SimpleItemProcessor;
import org.javacream.training.batch.spring.simple.simplestep.SimpleItemReader;
import org.javacream.training.batch.spring.simple.simplestep.SimpleItemWriter;
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

//@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Bean Step stepOneLine(SimpleItemReader reader, SimpleItemProcessor processor, SimpleItemWriter writer) {
		return stepBuilderFactory.get("step1").<String, Integer>chunk(3).reader(reader).processor(processor).writer(writer).build();
	}
	
	@Bean
	public Job job(Step step) throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("job2").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(step);
		return startJob.build();
	}


}

