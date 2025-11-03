package org.javacream.training.batch.second;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloMoonTasklet implements Tasklet{

	@Value("#{jobParameters['name']}")
	private String name;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		//var params = chunkContext.getStepContext().getJobParameters();
		//var greeting = params.get("name");
		System.out.println("Hello Moon from " + name);
		return RepeatStatus.FINISHED;
	}
	

}
