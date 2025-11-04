package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleItemProcessor implements ItemProcessor<String, Integer> {
    @Override
    public Integer process(String item) throws Exception {
        return item.length();
    }
}
