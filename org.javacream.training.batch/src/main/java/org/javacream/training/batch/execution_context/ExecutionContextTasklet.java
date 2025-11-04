package org.javacream.training.batch.execution_context;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ExecutionContextTasklet implements Tasklet {
	@Value("#{stepExecution}") private StepExecution stepExecution;
	//@Value("#{jobExecution}") private JobExecution jobExecution;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		var info = "executing step at " + System.currentTimeMillis();
		stepExecution.getExecutionContext().put("info", info);
		//jobExecution.getExecutionContext().put("info", info);
		System.out.println("#####  executionContext sample");
		return RepeatStatus.FINISHED;
	}

}
