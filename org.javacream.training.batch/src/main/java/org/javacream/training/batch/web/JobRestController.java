package org.javacream.training.batch.web;

import java.util.Properties;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

	@Autowired
	private JobLauncher launcher;

	@Autowired
	@Qualifier("helloWorld")
	private Job helloWorldJob;
	@Autowired
	@Qualifier("simpleSequence")
	private Job simpleSequenceJob;
	@Autowired
	@Qualifier("simpleChunkJob")
	private Job simpleChunkJob;
	@Autowired
	private Job simpleXmlChunkJob;

	@Autowired @Qualifier("fileChunkJob")
	private Job fileChunkJob;

	@PostMapping(path = "api/jobs", produces = MediaType.TEXT_PLAIN_VALUE)
	public String executeJob(@RequestBody JobLaunchRequest jobLaunchRequest) {
		System.out.println("received launch request " + jobLaunchRequest);
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		Properties props = jobLaunchRequest.getJobParameters();

		for (String paramname : props.stringPropertyNames()) {
			jobParametersBuilder.addString(paramname, props.getProperty(paramname), true);
		}
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		String jobName = jobLaunchRequest.getJobName();
		try {
			if ("helloWorld".equals(jobName)) {
				launcher.run(helloWorldJob, jobParameters);
			} else if ("simpleSequenceJob".equals(jobName)) {
				launcher.run(simpleSequenceJob, jobParameters);
			} else if ("simpleChunkJob".equals(jobName)) {
				launcher.run(simpleChunkJob, jobParameters);
			} else if ("simpleXmlChunkJob".equals(jobName)) {
				launcher.run(simpleXmlChunkJob, jobParameters);
			}
			else if ("fileChunkJob".equals(jobName)) {
				launcher.run(fileChunkJob, jobParameters);
			}

			// else -> Dispatching auf andere Jobs
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		return "OK";
	}

}