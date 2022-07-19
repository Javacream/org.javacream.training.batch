package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("processor")
@StepScope
public class SimpleItemProcessor implements ItemProcessor<String, Integer> {
	@Value("#{jobParameters['max']}") private int max;
	@Override
	public Integer process(String item) throws Exception {
		int length = item.length();
		if (length > max) {
			throw new IllegalAccessException("string too long");
		} else {
			return length;
		}
	}

}
