package org.javacream.training.batch.spring;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobLauncherRestartTests {
	
	@Autowired JobLauncher launcher;
	@Autowired Job job;

	@Test
	public void runJob() throws Exception, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters params = new JobParametersBuilder().addString("file", "run44").toJobParameters();
		launcher.run(job, params);
	}

}
