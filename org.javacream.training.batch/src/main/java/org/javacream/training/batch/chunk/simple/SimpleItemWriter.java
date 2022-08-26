package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer> {
    @Override
    public void write(List<? extends Integer> items) throws Exception {
        System.out.println(items);
    }
}
