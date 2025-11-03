package org.javacream.training.batch.web;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Map<String, Job> jobs;

	@GetMapping (path="api/jobs", produces=MediaType.APPLICATION_JSON_VALUE)
	public Set<String> jobNames(){
		return jobs.keySet();
	}
	
	@PostMapping(path = "api/jobs", produces = MediaType.TEXT_PLAIN_VALUE)
	public String executeJob(@RequestBody HttpJobLaunchRequest httpJobLaunchRequest) {
		System.out.println("received launch request " + httpJobLaunchRequest);
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		Properties props = httpJobLaunchRequest.getJobParameters();

		for (String paramName : props.stringPropertyNames()) {
			jobParametersBuilder.addString(paramName, props.getProperty(paramName), true);
		}
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		String jobName = httpJobLaunchRequest.getJobName();
		Job toExecute = jobs.get(jobName);
		if (toExecute == null) {
			return "unknown job: " + jobName;
		}
		try {
			launcher.run(jobs.get(jobName), jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		return "OK";
	}

}