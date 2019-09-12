package org.javacream.training.batch.spring;

import javax.sql.DataSource;

import org.javacream.training.batch.spring.simple.SimpleBusinessClass;
import org.javacream.training.batch.spring.simple.SimpleBusinessComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired private DataSource ds;
	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private SimpleBusinessComponent sbcomponent;
	@Autowired @Qualifier("var1") private SimpleBusinessClass sbclass;
	
	@Value ("${hugo}")private String demo;
	@Test
	public void contextLoads() {
		System.out.println("Datasource=" + ds);
		System.out.println("JobBuilderFactory=" + jobBuilderFactory);
		System.out.println("demo=" + demo);
		System.out.println("sbcomponent=" + sbcomponent);
		System.out.println("sbclass=" + sbclass.getDescription());
	}

}
