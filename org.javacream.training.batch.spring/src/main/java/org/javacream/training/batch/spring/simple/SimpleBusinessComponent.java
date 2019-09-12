package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SimpleBusinessComponent {
	
	@PostConstruct public void initTheComponent() {
		System.out.println("########################## initializing " + this);
	}

}
