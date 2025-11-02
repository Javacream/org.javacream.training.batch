package org.javacream.training.batch.file;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class FlatfileBatchConfiguration {
	@Autowired
	private JobRepository jobRepository;

	@Autowired PlatformTransactionManager transactionManager;
	@Bean
	ItemReader<Person> reader() {
		FlatFileItemReader<Person> itemReader = new FlatFileItemReader<Person>();
		itemReader.setResource(new FileSystemResource("src/data/in/people.csv"));
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
		return new StepBuilder("step1", jobRepository).<Person, Person>chunk(1, transactionManager).reader(reader).writer(writer)
				
				//.faultTolerant().skip(Exception.class).skipLimit(3)
				.build();
	}
	@Bean
	public Job job(Step step) throws Exception {
		JobBuilder jobBuilder = new JobBuilder("job", jobRepository).incrementer(new RunIdIncrementer());
		SimpleJobBuilder startJob = jobBuilder.start(step);
		return startJob.build();
	}

}