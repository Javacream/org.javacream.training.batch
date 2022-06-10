package org.javacream.training.batch.sequence;

import java.util.Random;

import org.javacream.training.batch.impl.SimpleBusinessObject;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class Step1Tasklet implements Tasklet {
	private long info = new Random().nextLong();
	@Value("#{jobParameters['forStep1']}")
	private String name;
	@Autowired JobData data;
	@Autowired SimpleBusinessObject simpleBusinessObject;
	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		String message = simpleBusinessObject.businessMethod1(name);
		data.setMessage(message + " with info " + info);
		return RepeatStatus.FINISHED;
	}

}
