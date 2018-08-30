package org.javacream.training.batch.spring.business.config;

public class Business1 {

	private DataAccessSimulator simulator;

	public void setSimulator(DataAccessSimulator simulator) {
		this.simulator = simulator;
	}

	public void init() {
		System.out.println("PostConstruct " + this + ", simulator=" + simulator);
	}

	{
		System.out.println("Initializing " + this + ", simulator=" + simulator);
	}

	public void doSomething() {
		System.out.println("doing something using " + this);
		System.out.println("simulator = " + simulator);
		simulator.saveToDatabase();

	}
}
