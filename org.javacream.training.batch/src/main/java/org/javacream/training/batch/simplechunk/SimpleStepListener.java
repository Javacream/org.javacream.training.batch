package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("stepListener")
@StepScope
public class SimpleStepListener implements StepExecutionListener {

	@Autowired private SimpleData simpleData;
	@Override
	public void beforeStep(StepExecution stepExecution) {
		simpleData.setExecutionContext(stepExecution.getExecutionContext());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	
}
