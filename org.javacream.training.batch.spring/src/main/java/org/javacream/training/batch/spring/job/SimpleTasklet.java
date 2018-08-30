package org.javacream.training.batch.spring.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleTasklet implements Tasklet {
	
	@Value("${javacream.simpletask.description}")
	private String description;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		System.out.println("Executing " + description + " using " + this);
		return RepeatStatus.FINISHED;
	}
}