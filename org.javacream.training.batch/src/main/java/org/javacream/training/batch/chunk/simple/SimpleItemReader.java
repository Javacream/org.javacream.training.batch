package org.javacream.training.batch.chunk.simple;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@Component
@StepScope
public class SimpleItemReader implements ItemReader<String> {

    private LinkedList<String> names;

    @BeforeStep public void beforeStep(StepExecution stepExecution){
        int writtenCount = stepExecution.getExecutionContext().getInt("written", 0);
        System.out.println("READ COUNT=" + writtenCount);
        for (int i =0; i < writtenCount; i++){
            names.removeLast();
        }
    }
    @PostConstruct public void init(){
        names = new LinkedList<>();
        names.add("Emil");
        names.add("Fritz");
        names.add("Hugo");
        names.add("Horst");
    }
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            return names.removeLast();
        }
        catch(NoSuchElementException e){
            return null;
        }
    }
}
