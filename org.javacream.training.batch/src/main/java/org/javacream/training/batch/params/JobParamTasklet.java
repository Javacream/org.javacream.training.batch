package org.javacream.training.batch.params;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@JobScope
public class JobParamTasklet implements Tasklet {

    @Value("#{jobParameters['param1']}")
    private String param1;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("param1=" + param1);
        return RepeatStatus.FINISHED;
    }
}
