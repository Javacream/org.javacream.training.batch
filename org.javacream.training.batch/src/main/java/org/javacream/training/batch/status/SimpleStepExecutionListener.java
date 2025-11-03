package org.javacream.training.batch.status;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleStepExecutionListener implements StepExecutionListener {

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		var exitStatus = stepExecution.getExitStatus();
		var exitCode = exitStatus.getExitCode();
		System.out.println("##### StepExecution exitCode=" + exitCode);
		return exitStatus;
	}

}
