package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleJobScheduler {
	@Autowired private JobLauncher jobLauncher;
	@Autowired Job job;

	@PostConstruct public void init() {
		JobParametersBuilder jpb = new JobParametersBuilder();
		jpb.addLong("egal", 42l);
		try {
			jobLauncher.run(job, jpb.toJobParameters());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
