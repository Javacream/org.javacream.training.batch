package org.javacream.training.batch.spring.simple;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemReader implements ItemReader<String> {

	private String prefix;
	
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	private LinkedList<String> names = new LinkedList<>();

	@PostConstruct
	public void initList() {
		names.add("Emil");
		names.add("Hannelore");
		names.add("Fridoling");
		names.add("Meier");
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return names.removeLast();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
