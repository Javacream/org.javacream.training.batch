package org.javacream.training.batch.spring;

import javax.annotation.Resource;

import org.javacream.training.batch.spring.business.config.Business1;
import org.javacream.training.batch.spring.business.config.BusinessConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessConfig.class)
public class ApplicationJavaConfigTests {

	@Resource(name = "business1")
	private Business1 business;

	@Test
	public void testBusiness1Spring() {
		business.doSomething();
		System.out.println("b1: " + business.hashCode());
	}

}
