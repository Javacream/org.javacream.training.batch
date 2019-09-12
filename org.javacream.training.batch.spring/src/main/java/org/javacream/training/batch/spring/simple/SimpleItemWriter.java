package org.javacream.training.batch.spring.simple;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemWriter implements ItemWriter<Integer>{

	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		System.out.println("WRITING NUMBERS: " + numbers);
	}

}
