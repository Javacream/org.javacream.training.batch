package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleChunkConfiguration {
    @Autowired private JobRepository jobRepository;
    @Autowired private PlatformTransactionManager transactionManager;
    @Autowired private SimpleItemReader reader;
    @Autowired private SimpleItemProcessor processor;
    @Autowired private SimpleItemWriter writer;
    @Bean
    public Step chunkStep(){
        return new StepBuilder("chunk", jobRepository).<String, Integer>chunk(3, transactionManager).reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    @Qualifier("chunkJob")
    public Job chunkJob(){
        return new JobBuilder("chunk-job", jobRepository).start(chunkStep()).build();
    }
}
