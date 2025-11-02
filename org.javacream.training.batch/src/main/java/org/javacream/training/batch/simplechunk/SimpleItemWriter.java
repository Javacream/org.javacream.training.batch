package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component("simpleWriter")
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer>{

	@Autowired SimpleData simpleData;
	
	@PostConstruct public void setUp() {
		System.out.println("################## " + simpleData.getMessage());
	}

	@Override
	public void write(Chunk<? extends Integer> chunk) throws Exception {
		int processed = simpleData.getProcessed();
		System.out.println(chunk);
		processed += chunk.size();
		simpleData.setProcessed(processed);
	}
	

}
