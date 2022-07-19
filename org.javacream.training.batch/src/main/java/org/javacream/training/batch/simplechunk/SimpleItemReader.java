package org.javacream.training.batch.simplechunk;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reader")
@JobScope
public class SimpleItemReader implements ItemReader<String> {


	@Autowired SimpleData simpleData;
	private LinkedList<String> names;
	private int processed;
	
	@PostConstruct public void init(){
		names = new LinkedList<>();
		names.add("Hugo");
		names.add("Eduard");
		names.add("Hannah");
		names.add("Fritz");
		names.add("Emil");
		names.add("Ada");
		names.add("Ida");
		simpleData.setMessage("processing names list");
		processed = simpleData.getProcessed();
		for (int i = 0; i < processed; i++) {
			String name = names.removeLast();
			System.out.println("Removing name: " + name + ", processed = " + processed);
		}
		
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
