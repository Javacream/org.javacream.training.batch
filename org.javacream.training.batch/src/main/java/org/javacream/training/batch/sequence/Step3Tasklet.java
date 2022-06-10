package org.javacream.training.batch.sequence;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Step3Tasklet implements Tasklet {

	@Autowired JobData data;

	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		System.out.println("step 3 writes property forStep3 "
				+ data.getMessage());
		return RepeatStatus.FINISHED;
	}


}
