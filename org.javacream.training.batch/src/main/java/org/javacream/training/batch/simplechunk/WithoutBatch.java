package org.javacream.training.batch.simplechunk;

import java.util.LinkedList;

public class WithoutBatch {

	public static void main(String[] args) {
		{
			LinkedList<String> names = new LinkedList<>();
			names.add("Hugo");
			names.add("Eduard");
			names.add("Hannah");
			names.add("Fritz");
			
			names.stream().map(name -> name.length()).forEach(System.out::println);
			
		}
	}
	
}
