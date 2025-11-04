package org.javacream.training.batch.execution_context;

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
public class ExecutionContextConfiguration {

	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
    @Autowired ExecutionContextTasklet tasklet;
    @Autowired SimpleBatchJobExecutionListener jobExecutionListener;
    @Autowired SimpleBatchStepExecutionListener stepExecutionListener;
	@Bean
    public Step executionContextStep(){
        return new StepBuilder("step1", jobRepository).tasklet(tasklet, transactionManager).listener(stepExecutionListener).build();
    }
    
    @Bean
    public Job executionContextJob(){
        return new JobBuilder("executionContext-job", jobRepository).start(executionContextStep()).listener(jobExecutionListener).build();
    }

}
