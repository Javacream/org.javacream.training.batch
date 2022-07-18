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
public class Step1Tasklet implements Tasklet{

	@Value("#{jobParameters['forStep1']}")
	private String forStep1;
	
	@Autowired private JobData data;
	@Override
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		data.setData("Greetings from Step1");
		System.out.println("forStep1: " + forStep1);
		return RepeatStatus.FINISHED;
	}

}
