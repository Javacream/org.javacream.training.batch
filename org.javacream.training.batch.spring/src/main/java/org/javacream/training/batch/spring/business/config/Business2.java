package org.javacream.training.batch.spring.business.config;

public class Business2 {

	
	
	private DataAccessSimulator simulator;
	public void setSimulator(DataAccessSimulator simulator) {
		this.simulator = simulator;
	}

	public void init() {
		System.out.println("Initializing " + this);
	}
	
	public void doSomething() {
		System.out.println("doing something using " + this);
		System.out.println("simulator = " + simulator);
		simulator.saveToDatabase();
		
	}
}
