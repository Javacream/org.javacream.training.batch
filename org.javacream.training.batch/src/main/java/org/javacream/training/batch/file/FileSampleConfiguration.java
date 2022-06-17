package org.javacream.training.batch.file;

import org.javacream.training.batch.simplechunk.SimpleProcessor;
import org.javacream.training.batch.simplechunk.SimpleReader;
import org.javacream.training.batch.simplechunk.SimpleWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@EnableBatchProcessing
public class FileSampleConfiguration {

	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;

	@Bean @StepScope public FlatFileItemReader<String> fileReader(){
		Resource input = new FileSystemResource("src/data/input/input.txt");
		LineMapper<String> mapper = new PassThroughLineMapper();
		return new FlatFileItemReaderBuilder<String>().lineMapper(mapper).resource(input).targetType(String.class).build();
	}
	@Bean @StepScope public FlatFileItemWriter<Integer> fileWriter(){
		Resource output = new FileSystemResource("src/data/input/output.txt");
		LineAggregator<Integer> aggregator = new PassThroughLineAggregator<Integer>();
		return new FlatFileItemWriterBuilder<Integer>().resource(output).lineAggregator(aggregator).build();
		
	}

	
	@Bean public Step fileChunkStep(SimpleProcessor processor) {
		return stepBuilderFactory.get("chunk").<String, Integer>chunk(2).reader(fileReader()).processor(processor).writer(fileWriter()).allowStartIfComplete(true).build();
	}
	@Bean @Qualifier("fileChunkJob") public Job fileChunkJob(SimpleReader reader, SimpleWriter writer, SimpleProcessor processor) {
		return jobBuilderFactory.get("chunkJob").start(fileChunkStep(processor)). build();
	}
}
