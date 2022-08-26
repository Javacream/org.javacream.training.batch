package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer> {
    private String shouldThrow;
    private StepExecution stepExecution;
    @BeforeStep
    public void beforeStep(StepExecution stepExecution){
        this.stepExecution = stepExecution;
        shouldThrow = stepExecution.getExecutionContext().getString("shouldThrow", "y");
        System.out.println("SHOULD THROW=" + shouldThrow);
    }

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        int written = stepExecution.getExecutionContext().getInt("written", 0);
        int commitCount = stepExecution.getCommitCount();
        System.out.println("commitCount=" + commitCount);
        //provoke rollback during first execution after first successful commit
        if (commitCount == 1 && "y".equals(shouldThrow)){
            stepExecution.getExecutionContext().putString("shouldThrow", "n");
            throw new IllegalArgumentException("ROLLBACK");
        }
        written += items.size();
        stepExecution.getExecutionContext().putInt("written", written);
        System.out.println(items);
    }
}
