package org.javacream.training.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		Step simpleChunkStep = stepBuilderFactory.get("simpleChunk").<String, Integer>chunk(3).reader(fileReader(null, null))
				.processor(nameToSizeProcessor()).writer(fileWriter(null, null)).build();
		return simpleChunkStep;

	}

	@Bean
	@Qualifier("simpleChunk")
	public Job simpleChunkJob() {
		return jobBuilderFactory.get("simpleChunk").start(simpleChunkStep()).validator(new SimpleChunkJobParameterValidator()).build();
	}


	@Bean @StepScope
	public NameToSizeProcessor nameToSizeProcessor() {
		return new NameToSizeProcessor();
	}

	public static class NameToSizeProcessor implements ItemProcessor<String, Integer> {

		@Override
		public Integer process(String item) throws Exception {
			return item.length();
		}

	}

	@Bean @StepScope public FlatFileItemReader<String> fileReader(@Value("#{jobParameters['inFile']}") String inputFile, JobData jobData){
		//JobData jobData = new JobData();
		jobData.setMessage("Hello from Reader!");
		Resource resource = new FileSystemResource("src/data/input/" + inputFile);
		return new FlatFileItemReaderBuilder<String>().lineMapper(new PassThroughLineMapper()).addComment("#").resource(resource).name("nameFileReader").targetType(String.class).build();
	}
	@Bean @StepScope public FlatFileItemWriter<Integer> fileWriter(@Value("#{jobParameters['outFile']}") String outputFile, JobData jobData){
		System.out.println(jobData.getMessage());
		Resource resource = new FileSystemResource("src/data/output/" + outputFile);
		return new FlatFileItemWriterBuilder<Integer>().name("nameFileWriter").lineAggregator(new PassThroughLineAggregator<Integer>()).resource(resource).build();
	}

	public static class SimpleChunkJobParameterValidator implements JobParametersValidator{

		@Override
		public void validate(JobParameters parameters) throws JobParametersInvalidException {
			if (parameters.getString("inFile")== null || parameters.getString("outFile") == null) {
				throw new JobParametersInvalidException("inFile and outFile must be set");
			}
		}
		
	}
}
