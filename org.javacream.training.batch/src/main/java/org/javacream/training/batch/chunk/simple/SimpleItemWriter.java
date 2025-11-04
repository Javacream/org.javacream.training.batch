package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer> {

	@Override
	public void write(Chunk<? extends Integer> chunk) throws Exception {
        System.out.println(chunk.getItems());
		
	}
}
