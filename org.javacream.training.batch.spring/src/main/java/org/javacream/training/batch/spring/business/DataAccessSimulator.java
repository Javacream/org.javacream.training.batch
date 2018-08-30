package org.javacream.training.batch.spring.business;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") //singleton ist default!
public class DataAccessSimulator {
	
	public void saveToDatabase() {
		System.out.println("saving... using " + this);
		
	}
}
