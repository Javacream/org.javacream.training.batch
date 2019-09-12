package org.javacream.training.batch.spring.simple.simplestep;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleItemWriter implements ItemWriter<Integer>{

	@Autowired StepData stepData;

	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
	
		System.out.println("WRITING NUMBERS: " + numbers + " with data " + stepData.getData());
	}

}
