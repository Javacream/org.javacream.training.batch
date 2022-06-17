package org.javacream.training.batch.simplechunk;


import java.util.List;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class SimpleWriter implements ItemWriter<Integer>{

	private int processed;
	@Value("#{jobExecution.executionContext}") ExecutionContext executionContext;

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		processed = executionContext.getInt("processed", 0);
		System.out.println(items);
		processed = processed + items.size();
		executionContext.putInt("processed", processed);			

	}
	

}
