package org.javacream.training.batch.impl;

import org.springframework.stereotype.Service;

@Service
public class SimpleBusinessObject {

	public String businessMethod1(String name) {
		System.out.println("businessMethod1 receives " + name);
		return "greetings from businessMethod1";
	}
	public void businessMethod2(String name) {
		System.out.println("businessMethod2 receives " + name);
	}
	public void businessMethod3(String name, String message) {
		System.out.println("businessMethod3 receives name " + name + " and message " + message);
	}
}
