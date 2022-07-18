package org.javacream.training.batch.sequence;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope

public class Step3Tasklet implements Tasklet{
	@Value("#{jobParameters['forStep3']}")
	private String forStep3;

	@Autowired private JobData data;

	@Override
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("forStep3: " + forStep3);
		System.out.println("data: " + data.getData());
		return RepeatStatus.FINISHED;
	}

}
