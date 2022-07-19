package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("processor")
@StepScope
public class SimpleItemProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(String item) throws Exception {
		int length = item.length();
		if (length > 4) {
			throw new IllegalAccessException("string too long");
		} else {
			return length;
		}
	}

}
