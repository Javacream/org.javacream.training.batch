package org.javacream.training.batch.status;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@StepScope
public class StatusTasklet implements Tasklet {

	@Value("#{jobParameters['exitStatusSwitch']}") private String exitStatusSwitch;
	@PostConstruct public void init() {
		System.out.println("############### creating FirstTasklet");
	}
	
	private int counter;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("executing status tasklet, exitStatusSwitch=" + exitStatusSwitch + ", counter=" + counter);
		counter++;
		if (counter % 3 == 0) {
			if ("shouldFail".equals(exitStatusSwitch)) {
				throw new IllegalStateException(exitStatusSwitch);
			}
			return RepeatStatus.FINISHED;
		}
		else {
			return RepeatStatus.CONTINUABLE;
		}
	}
}
