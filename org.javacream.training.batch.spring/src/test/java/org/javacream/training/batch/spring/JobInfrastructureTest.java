package org.javacream.training.batch.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JobInfrastructureTest {

	@Autowired private JobRegistry registry;
	@Autowired private JobRepository repository;
	@Autowired private JobExplorer explorer;
	@Autowired private JobLauncher launcher;
	@Autowired private JobOperator operator;
	
	@Autowired Job job;
	
	@Test public void testOperator() {
		//operator.st
		//CRUD + Implizit
	}
	@Test public void testLauncher() {
		//launcher.
		//Implizites Erzeugen einer Job-Instanz
	}

	@Test public void testRepository() {
		//repository.
		//CRUD-Operationen auf der Batch-Datenbank
	}
	@Test public void testExplorer() {
		//explorer.
		//Read-Operationen auf der Batch-Datenbank
	}
	@Test public void testRegistry() throws Exception{
		registry.register(new JobFactory() {
			
			@Override
			public String getJobName() {
				return "JOB_NAME_IN_REGISTRY";
			}
			
			@Override
			public Job createJob() {
				return job;
			}
		});
	}
}
