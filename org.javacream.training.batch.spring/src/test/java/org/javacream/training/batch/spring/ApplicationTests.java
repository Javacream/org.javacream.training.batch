package org.javacream.training.batch.spring;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired private DataSource ds;
	@Autowired private JobBuilderFactory jobBuilderFactory;
	
	@Value ("${hugo}")private String demo;
	@Test
	public void contextLoads() {
		System.out.println("Datasource=" + ds);
		System.out.println("JobBuilderFactory=" + jobBuilderFactory);
		System.out.println("demo=" + demo);
	}

}
