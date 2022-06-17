package org.javacream.training.batch.simplechunk;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleReader implements ItemReader<String>{

	private LinkedList<String> names;
	@PostConstruct public void initData() {
		names = new LinkedList<>();
		names.add("Fritz");
		names.add("Emil");
		names.add("Hugo");

		
	}
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return names.removeLast();
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}

}
