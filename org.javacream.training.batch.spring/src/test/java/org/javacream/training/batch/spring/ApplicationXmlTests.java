package org.javacream.training.batch.spring;

import javax.annotation.Resource;

import org.javacream.training.batch.spring.business.xml.Business1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ApplicationXmlTests {

	@Resource(name="business1") 
	//@Autowired 
	private Business1 business;

//	@Test
//	public void testBusiness1NotSpring() {
//		Business1 b1 = new Business1();
//		b1.init();
//		b1.doSomething();
//	}
	@Test
	public void testBusiness1Spring() {
		business.doSomething();
		System.out.println("b1: " + business.hashCode());
	}

}
