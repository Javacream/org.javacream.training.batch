package org.javacream.training.batch.status;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleJobExecutionListener implements JobExecutionListener {

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("##### JobExecution exitCode=" + jobExecution.getExitStatus());
	}

}
