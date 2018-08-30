package org.javacream.training.batch.spring.job;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class SimpleReader implements ItemReader<String> {
	private LinkedList<String> data;

	@PostConstruct 
	public void init() {
		data = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			data.add("Item" + i);
		}
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return data.removeFirst();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

}
