package org.javacream.training.batch.spring.simple;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleItemProcessor implements ItemProcessor<String, Integer>{

	@Autowired StepData stepData;
	@Override
	public Integer process(String item) throws Exception {
		System.out.println("STEPDATA: " + stepData.getDescription());
		return item.length();
	}

}
