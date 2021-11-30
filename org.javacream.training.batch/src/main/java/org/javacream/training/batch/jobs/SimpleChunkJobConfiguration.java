package org.javacream.training.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SimpleChunkJobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@Qualifier("simpleChunk")
	public Step simpleChunkStep() {
		Step simpleChunkStep = stepBuilderFactory.get("simpleChunk").<String, Integer>chunk(3).reader(fileReader())
				.processor(nameToSizeProcessor()).writer(fileWriter()).build();
		return simpleChunkStep;

	}

	@Bean
	@Qualifier("simpleChunk")
	public Job simpleChunkJob() {
		return jobBuilderFactory.get("simpleChunk").start(simpleChunkStep()).build();
	}


	@Bean
	public NameToSizeProcessor nameToSizeProcessor() {
		return new NameToSizeProcessor();
	}

	public static class NameToSizeProcessor implements ItemProcessor<String, Integer> {

		@Override
		public Integer process(String item) throws Exception {
			return item.length();
		}

	}

	@Bean public FlatFileItemReader<String> fileReader(){
		Resource resource = new FileSystemResource("src/data/input/names.txt");
		return new FlatFileItemReaderBuilder<String>().lineTokenizer(new DelimitedLineTokenizer()).addComment("#").resource(resource).name("nameFileReader").targetType(String.class).build();
	}
	@Bean public FlatFileItemWriter<Integer> fileWriter(){
		Resource resource = new FileSystemResource("src/data/output/nameLenth.txt");
		return new FlatFileItemWriterBuilder<Integer>().name("nameFileWriter").lineAggregator(new PassThroughLineAggregator<Integer>()).resource(resource).build();
	}

}
