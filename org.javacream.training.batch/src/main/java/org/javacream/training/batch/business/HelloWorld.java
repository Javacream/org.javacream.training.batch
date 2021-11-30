package org.javacream.training.batch.business;

import org.springframework.stereotype.Service;

@Service
public class HelloWorld {

	public void sayHello() {
		System.out.println("Hello World from Service");
	}
}
