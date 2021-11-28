package org.javacream.training.batch.web;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

	@Autowired private JobLauncher launcher;
	@Autowired private Job job;
	private JobParametersBuilder jobParametersBuilder;
	
	@PostConstruct public void init() {
		jobParametersBuilder = new JobParametersBuilder();
	}
	@GetMapping(path = "/api/jobs/helloworld", produces = MediaType.TEXT_PLAIN_VALUE) public String executeHelloWorldJob() {
		
		try {
			launcher.run(job, jobParametersBuilder.addLong("run.timestamp", System.currentTimeMillis()).toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "OK";
	}
}
