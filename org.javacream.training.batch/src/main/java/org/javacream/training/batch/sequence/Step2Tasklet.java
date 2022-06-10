package org.javacream.training.batch.sequence;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step2Tasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		System.out.println("step 2 writes property forStep2 "
				+ chunkContext.getStepContext().getJobParameters().get("forStep2"));
		return RepeatStatus.FINISHED;
	}


}
