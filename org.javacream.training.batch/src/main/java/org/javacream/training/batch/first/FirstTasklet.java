package org.javacream.training.batch.first;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FirstTasklet implements Tasklet {

	@PostConstruct public void init() {
		System.out.println("############### creating FirstTasklet");
	}
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		var name = chunkContext.getStepContext().getJobParameters().get("name");
		System.out.println("Hello World from " + name);
		return RepeatStatus.FINISHED;
	}
}
