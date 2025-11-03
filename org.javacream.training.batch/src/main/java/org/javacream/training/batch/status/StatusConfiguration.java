package org.javacream.training.batch.status;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StatusConfiguration {

	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
    @Autowired StatusTasklet statusTasklet;
    @Autowired SimpleStepExecutionListener stepExecutionListener;
    @Autowired SimpleJobExecutionListener jobExecutionListener;
	
    @Bean
    public Step status(){
        return new StepBuilder("status", jobRepository).tasklet(statusTasklet, transactionManager).listener(stepExecutionListener).build();
    }
    
    @Bean
    public Job statusJob(){
        return new JobBuilder("status-job", jobRepository).listener(jobExecutionListener).start(status()).build();
    }

}
