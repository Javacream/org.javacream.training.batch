package org.javacream.training.batch.spring.simple.simplestep;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope	
public class SimpleItemReader implements ItemReader<String> {

	@Autowired StepData stepData;
	@Value("#{jobParameters['date']}") private String jobDate;
	private LinkedList<String> elements;

	@PostConstruct public void initList() {
		System.out.println(jobDate);
		elements = new LinkedList<>();
		elements.add("Hugo" + jobDate);
		elements.add("Fridolin");
		elements.add("Gregor");
		elements.add("Fritz");
		stepData.setData("Hello from Reader");
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
