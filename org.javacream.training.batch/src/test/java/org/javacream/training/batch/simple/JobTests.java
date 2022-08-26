package org.javacream.training.batch.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;


@SpringBootTest()
@SpringBatchTest
public class JobTests {
    @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

    @Test public void testJob() throws Exception{
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
        Assertions.assertEquals("chunk-file-job", actualJobInstance.getJobName());
        Assertions.assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
    }

    @Test public void jobHasOneStep() throws Exception{
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        Assertions.assertEquals(1, stepExecutions.size());
        stepExecutions.forEach(stepExecution -> {
            Assertions.assertEquals(ExitStatus.COMPLETED, stepExecution.getExitStatus());
        });
    }


}
