package org.javacream.training.batch.simplechunk;


import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleWriter implements ItemWriter<String>{

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println(items);
	}
	

}
