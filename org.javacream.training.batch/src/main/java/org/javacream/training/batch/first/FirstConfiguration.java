package org.javacream.training.batch.first;

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
public class FirstConfiguration {

	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
    @Autowired FirstTasklet firstTasklet;
	@Bean
    public Step step1(){
    	System.out.println("############## creating step1");
        return new StepBuilder("step1", jobRepository).tasklet(firstTasklet, transactionManager).build();
    }
    
    @Bean
    @Qualifier("helloWorld")
    public Job helloWorldJob(){
    	System.out.println("############## creating hello-world-job");
        return new JobBuilder("hello-world-job", jobRepository).start(step1()).build();
    }

}
