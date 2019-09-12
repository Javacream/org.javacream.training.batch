package org.javacream.training.batch.spring.simple;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemReader implements ItemReader<String> {

	private LinkedList<String> elements;

	@PostConstruct public void initList() {
		elements = new LinkedList<>();
		elements.add("Hugo");
		elements.add("Fridolin");
		elements.add("Gregor");
		elements.add("Fritz");
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return elements.removeLast();
		} catch (Exception e) {
			return null; //null: Kriterium: Keine weiteren Items vorhanden
		}
	}

}
