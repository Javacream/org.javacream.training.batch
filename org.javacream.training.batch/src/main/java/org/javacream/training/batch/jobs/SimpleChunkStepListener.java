package org.javacream.training.batch.jobs;

import javax.batch.runtime.StepExecution;

import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class SimpleChunkStepListener {
	
	@BeforeStep
	public void invokedBeforeStep(StepExecution stepExecution) {
		System.out.println("before step " + stepExecution.getStepName());
	}
	@AfterStep
	public String invokedAfterStep(StepExecution stepExecution) {
		System.out.println("after step " + stepExecution.getStepName());
		//return stepExecution.getExitStatus();
		return "Hugo";
	}

}
