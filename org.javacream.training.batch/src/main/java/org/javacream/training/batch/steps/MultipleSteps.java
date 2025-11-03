package org.javacream.training.batch.steps;

import org.javacream.training.batch.second.HelloMoonTasklet;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MultipleSteps {

	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;

	@Bean public Step step1() {
		return new StepBuilder("step1", jobRepository).tasklet((contribution, chunkContext) -> {
			chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("HUGO"));
			System.out.println("step1");
			return RepeatStatus.FINISHED;
		}, transactionManager).build();
	}

	@Bean public Step step2() {
		return new StepBuilder("step2", jobRepository).tasklet((contribution, chunkContext) -> {
			System.out.println("step2");
			return RepeatStatus.FINISHED;
		}, transactionManager).build();
	}
	@Bean public Step step3() {
		return new StepBuilder("step3", jobRepository).tasklet((contribution, chunkContext) -> {
			System.out.println("step3");
			return RepeatStatus.FINISHED;
		}, transactionManager).build();
	}
    
    @Bean
    @Qualifier("multiStepJob")
    public Job multiStepJob(){
        return new JobBuilder("multi-step-job", jobRepository).start(step1()).on("HUGO").to(step2()).next(step3()).end().build();
    }

}
