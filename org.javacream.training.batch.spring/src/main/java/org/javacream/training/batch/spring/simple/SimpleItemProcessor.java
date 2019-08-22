package org.javacream.training.batch.spring.simple;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemProcessor implements ItemProcessor<String, Integer>{

	@Override
	public Integer process(String item) throws Exception {
		return item.length();
	}

}
