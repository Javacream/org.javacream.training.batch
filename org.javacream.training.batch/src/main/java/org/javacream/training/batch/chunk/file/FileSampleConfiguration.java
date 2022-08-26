package org.javacream.training.batch.chunk.file;

import org.javacream.training.batch.chunk.simple.SimpleItemProcessor;
import org.javacream.training.batch.chunk.simple.SimpleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class FileSampleConfiguration {

    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    SimpleItemWriter simpleItemWriter;

    @Autowired
    SimpleItemProcessor simpleItemProcessor;
    @Bean @StepScope public FlatFileItemReader<String> fileReader(){
        //return new FlatFileItemReader<String>("src/data/names.txt");
        Resource input = new FileSystemResource("org.javacream.training.batch/src/data/names.txt");
        LineMapper<String> mapper = new PassThroughLineMapper();
        return new FlatFileItemReaderBuilder<String>().lineMapper(mapper).resource(input).targetType(String.class).name("flatFileReader").build();
    }

    @Bean public Step fileChunkStep(){
        return stepBuilderFactory.get("fileChunk").<String, Integer>chunk(3).reader(fileReader()).processor(simpleItemProcessor).writer(simpleItemWriter).build();
    }

    @Bean
    @Qualifier("chunkFileJob")
    public Job chunkFileJob(){
        return jobBuilderFactory.get("chunk-file-job").start(fileChunkStep()).build();
    }

}
