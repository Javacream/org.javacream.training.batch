package org.javacream.training.batch.spring.business.config;

import javax.annotation.PostConstruct;

public class Business2 {

	
	
	private DataAccessSimulator simulator;
	public void setSimulator(DataAccessSimulator simulator) {
		this.simulator = simulator;
	}

	@PostConstruct public void init() {
		System.out.println("PostConstruct " + this + ", simulator=" + simulator);
	}
	
	public void doSomething() {
		System.out.println("doing something using " + this);
		System.out.println("simulator = " + simulator);
		simulator.saveToDatabase();
		
	}
}
