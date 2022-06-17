package org.javacream.training.batch.simplechunk;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class SimpleReader implements ItemReader<String>{

	@Value("#{jobExecution.executionContext}") ExecutionContext executionContext;
	private LinkedList<String> names;
	private int processed;
	private int shouldThrow;
	@PostConstruct public void initData() {
		processed = executionContext.getInt("processed", 0);
		shouldThrow = executionContext.getInt("shouldThrow", 0);
		names = new LinkedList<>();
		names.add("Fritz");
		names.add("Emil");
		names.add("Hugo");

		
	}
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("################: processed: " + processed + ", shouldThrow:" + shouldThrow);
		try {
			String name = names.removeLast();
			if (shouldThrow == 0 && "Emil".equals(name)) {
				executionContext.putInt("shouldThrow", 1);			
				throw new IllegalArgumentException("Emil is not valid");
			}
			processed++;
			executionContext.putInt("processed", processed);			
			return name;
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}

}
