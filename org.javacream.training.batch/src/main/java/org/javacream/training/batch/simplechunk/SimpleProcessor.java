package org.javacream.training.batch.simplechunk;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcessor implements ItemProcessor<String, Integer>{

	@Override
	public Integer process(String item) throws Exception {
		return item.length();
	}

}
