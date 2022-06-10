package org.javacream.training.batch.sequence;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step3Tasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		System.out.println("step 3 writes property forStep3 "
				+ chunkContext.getStepContext().getJobParameters().get("forStep3") + " and greetings " + chunkContext.getStepContext().getJobExecutionContext().get("fromStep1"));
		return RepeatStatus.FINISHED;
	}


}
