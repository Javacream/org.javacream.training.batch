package org.javacream.training.batch.jobs;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
		Step simpleChunkStep = stepBuilderFactory.get("simpleChunk").<String, Integer>chunk(3).reader(namesItemReader())
				.processor(nameToSizeProcessor()).writer(namesSizeWriter()).build();
		return simpleChunkStep;

	}

	@Bean
	@Qualifier("simpleChunk")
	public Job simpleChunkJob() {
		return jobBuilderFactory.get("simpleChunk").start(simpleChunkStep()).build();
	}

	@Bean
	public NamesItemReader namesItemReader() {
		return new NamesItemReader();
	}

	@Bean
	public NameSizeWriter namesSizeWriter() {
		return new NameSizeWriter();
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

	public static class NameSizeWriter implements ItemWriter<Integer> {

		@Override
		public void write(List<? extends Integer> items) throws Exception {
			System.out.println(items);
		}

	}

	@Bean public FlatFileItemReader<String> fileReader(){
		ClassPathResource resource = new ClassPathResource("names.txt");
		return new FlatFileItemReaderBuilder<String>().addComment("#").resource(resource).targetType(String.class).build();
	}
	@Bean public FlatFileItemWriter<String> fileWriter(){
		ClassPathResource resource = new ClassPathResource("nameLength.txt");
		return new FlatFileItemWriterBuilder<String>().resource(resource).build();
	}

	public static class NamesItemReader implements ItemReader<String> {
		private LinkedList<String> names = new LinkedList<>();

		@PostConstruct
		public void initNames() {
			names.add("Hugo");
			names.add("Emil");
			names.add("Franz");
			names.add("Martha");
			names.add("Julia");
			names.add("Michelle");
			names.add("Anna");
		}

		@Override
		public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if (names.size() == 0) {
				return null;
			}

			return names.removeFirst();
		}

	}
}
