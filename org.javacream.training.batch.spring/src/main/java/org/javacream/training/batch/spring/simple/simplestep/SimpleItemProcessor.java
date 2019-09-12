package org.javacream.training.batch.spring.simple.simplestep;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleItemProcessor implements ItemProcessor<String, Integer>{

	@Override
	public Integer process(String string) throws Exception {
		return string.length();
	}

}
