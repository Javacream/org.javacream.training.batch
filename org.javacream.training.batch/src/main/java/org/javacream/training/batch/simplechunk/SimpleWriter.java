package org.javacream.training.batch.simplechunk;


import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleWriter implements ItemWriter<Integer>{

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		System.out.println(items);
	}
	

}