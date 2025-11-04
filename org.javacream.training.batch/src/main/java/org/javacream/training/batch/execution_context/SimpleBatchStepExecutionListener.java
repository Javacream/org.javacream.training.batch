package org.javacream.training.batch.execution_context;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleBatchStepExecutionListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("step: " + stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("step: " + stepExecution);
		return stepExecution.getExitStatus();
	}

}
