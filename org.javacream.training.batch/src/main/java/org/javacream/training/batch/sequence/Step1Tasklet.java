package org.javacream.training.batch.sequence;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step1Tasklet implements Tasklet {
	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		System.out.println(
				"step 1 writes property forStep1 " + chunkContext.getStepContext().getJobParameters().get("forStep1"));
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("fromStep1",
				"greetings from step 1!");
		return RepeatStatus.FINISHED;
	}

}
