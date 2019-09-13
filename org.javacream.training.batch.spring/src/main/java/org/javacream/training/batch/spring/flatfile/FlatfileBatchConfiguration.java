package org.javacream.training.batch.spring.flatfile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class FlatfileBatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	ItemReader<Person> reader() {
		FlatFileItemReader<Person> itemReader = new FlatFileItemReader<Person>();
		itemReader.setResource(new FileSystemResource("data/in/people.csv"));
		DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<Person>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper((fieldSet) -> {
			Person p = new Person();
			p.setFirstname(fieldSet.readString(0));
			p.setLastname(fieldSet.readString(1));
			return p;
		});
		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}

	@Bean
	ItemWriter<Person> writer() {
		return new FlatFileItemWriterBuilder<Person>().name("itemWriter")
				.resource(new FileSystemResource("data/out/people.txt"))
				.lineAggregator(new PassThroughLineAggregator<Person>()).build();
	}

	@Bean Step step(ItemReader<Person> reader, ItemWriter<Person> writer) {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(3).faultTolerant().skipLimit(5).skip(Exception.class).reader(reader).writer(writer).build();
	}
	@Bean
	public Job job(Step step) throws Exception {
		JobBuilder jobBuilder = jobBuilderFactory.get("filejob").incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(step);
		return startJob.build();
	}

}
