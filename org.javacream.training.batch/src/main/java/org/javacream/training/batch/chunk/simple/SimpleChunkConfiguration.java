package org.javacream.training.batch.chunk.simple;

import org.javacream.training.batch.simple.HelloWorldTasklet;
import org.javacream.training.batch.simple.Step1Tasklet;
import org.javacream.training.batch.simple.Step2Tasklet;
import org.javacream.training.batch.simple.Step3Tasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableBatchProcessing
public class SimpleChunkConfiguration {
    @Autowired StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired private SimpleItemReader reader;
    @Autowired private SimpleItemProcessor processor;
    @Autowired private SimpleItemWriter writer;
    @Bean
    public Step chunkStep(){
        return stepBuilderFactory.get("chunk").<String, Integer>chunk(3).reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    @Qualifier("chunkJob")
    public Job chunkJob(){
        return jobBuilderFactory.get("chunk-job").start(chunkStep()).build();
    }
}
