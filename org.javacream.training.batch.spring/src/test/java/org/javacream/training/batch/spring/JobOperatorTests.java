package org.javacream.training.batch.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobOperatorTests {

	@Autowired
	JobOperator operator;
	@Autowired
	JobRegistry registry;
	@Autowired
	JobRepository repository;
	@Autowired
	Job job;

	@Test
	public void runJob()
			throws Exception, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		registry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "job2";
			}

			@Override
			public Job createJob() {
				return job;
			}
		});
		JobParameters params = new JobParametersBuilder().addString("name", "HUGO0").toJobParameters();
		JobInstance instance = repository.createJobInstance("job2", params);
		System.out.println(operator.getJobNames());

	}

}
