package org.javacream.training.batch.simple;

import org.springframework.batch.core.ExitStatus;
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
public class Step1Tasklet implements Tasklet {
    @Value("#{stepExecution}") private StepExecution stepExecution;
    @Value("#{jobParameters['name']}") private String name;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello from Step1");
        stepExecution.setExitStatus(new ExitStatus(name));
        return RepeatStatus.FINISHED;
    }
}
