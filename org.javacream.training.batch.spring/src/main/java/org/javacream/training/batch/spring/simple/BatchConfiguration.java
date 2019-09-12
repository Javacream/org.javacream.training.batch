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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Bean @Qualifier("step2") 
	Step stepTwoLines(SimpleItemReader reader, SimpleItemProcessor processor, SimpleItemWriter writer) {
		SimpleStepBuilder<String, Integer> builder = stepBuilderFactory.get("step1").chunk(2);
		builder.reader(reader).processor(processor).writer(writer);
		return builder.build();
	}
	@Bean @Qualifier("step1") Step stepOneLine(SimpleItemReader reader, SimpleItemProcessor processor, SimpleItemWriter writer) {
		return stepBuilderFactory.get("step1").<String, Integer>chunk(3).reader(reader).processor(processor).writer(writer).build();
	}
	
	@Bean
	public Job job(@Qualifier("step2") Step step) throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(step);
		return startJob.build();
	}
}
