package org.javacream.training.batch.simplechunk;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reader")
@StepScope
public class SimpleItemReader implements ItemReader<String> {

	@Autowired SimpleData simpleData;
	private LinkedList<String> names;
	
	@PostConstruct public void init(){
		names = new LinkedList<>();
		names.add("Hugo");
		names.add("Eduard");
		names.add("Hannah");
		names.add("Fritz");
		simpleData.setMessage("processing names list");
		
	}
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		try {
			return names.removeLast();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

}