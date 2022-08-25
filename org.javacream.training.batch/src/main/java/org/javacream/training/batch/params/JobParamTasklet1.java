package org.javacream.training.batch.params;

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
public class JobParamTasklet1 implements Tasklet {

    @Autowired Data data;
    @Value("#{jobParameters['param1']}")
    private String param1;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("param1=" + param1);
        data.setData("greetings from step1!");
        return RepeatStatus.FINISHED;
    }
}
