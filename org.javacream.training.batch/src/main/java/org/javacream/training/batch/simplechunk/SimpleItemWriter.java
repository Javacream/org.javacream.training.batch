package org.javacream.training.batch.simplechunk;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("writer")
@JobScope
public class SimpleItemWriter implements ItemWriter<Integer>{

	@Autowired SimpleData simpleData;
	@Value("#{jobExecution.executionContext}") private ExecutionContext context;
	
	@PostConstruct public void setUp() {
		System.out.println("################## " + simpleData.getMessage());
	}
	@Override
	public void write(List<? extends Integer> items) throws Exception {
		int processed = context.getInt("processed", 0);
		System.out.println(items);
		processed += items.size();
		context.putInt("processed", processed);
	}
	

}
