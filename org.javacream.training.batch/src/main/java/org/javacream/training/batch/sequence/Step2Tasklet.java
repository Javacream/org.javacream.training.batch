package org.javacream.training.batch.sequence;

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
public class Step2Tasklet implements Tasklet {
	@Autowired SimpleBusinessObject simpleBusinessObject;

	@Value("#{jobParameters['forStep2']}")
	private String name;
	@Override
	public RepeatStatus execute(StepContribution contribution,
			org.springframework.batch.core.scope.context.ChunkContext chunkContext) throws Exception {
		simpleBusinessObject.businessMethod2(name);
		return RepeatStatus.FINISHED;
	}


}
