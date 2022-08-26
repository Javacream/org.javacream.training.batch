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

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        System.out.println(items);
    }
}
