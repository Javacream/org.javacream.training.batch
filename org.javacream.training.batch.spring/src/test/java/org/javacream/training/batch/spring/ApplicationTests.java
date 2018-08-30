package org.javacream.training.batch.spring;

import org.javacream.training.batch.spring.business.Business1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired 
	private Business1 business;

	@Test
	public void testBusiness1Spring() {
		business.doSomething();
		System.out.println("b1: " + business.hashCode());
	}


}
