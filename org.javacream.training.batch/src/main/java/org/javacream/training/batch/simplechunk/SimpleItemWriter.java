package org.javacream.training.batch.simplechunk;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("writer")
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer>{

	@Autowired SimpleData simpleData;
	
	@PostConstruct public void setUp() {
		System.out.println("################## " + simpleData.getMessage());
	}
	@Override
	public void write(List<? extends Integer> items) throws Exception {
		int processed = simpleData.getProcessed();
		System.out.println(items);
		processed += items.size();
		simpleData.setProcessed(processed);
	}
	

}
