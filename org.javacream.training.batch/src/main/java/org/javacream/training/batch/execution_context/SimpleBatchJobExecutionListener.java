package org.javacream.training.batch.execution_context;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleBatchJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job before: " + jobExecution);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("job after: " + jobExecution);
	}

}
