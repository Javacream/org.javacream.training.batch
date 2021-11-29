package org.javacream.training.batch.web;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

	@Autowired
	private JobLauncher launcher;
	
	@Autowired @Qualifier("helloWorld")  private Job helloWorldJob;
	
	@GetMapping(path = "api/jobs/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String executeJob(@PathVariable("name") String jobName) {
		System.out.println("received name " + jobName);
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		JobParameters jobParameters = jobParametersBuilder.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters();
		try {
			if ("helloWorld".equals(jobName)) {
			launcher.run(helloWorldJob, jobParameters);
			}//else -> Dispatching auf andere Jobs
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "OK";
	}

}
