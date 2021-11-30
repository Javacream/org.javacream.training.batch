package org.javacream.training.batch.jobs;

import org.javacream.training.batch.business.HelloWorld;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired private HelloWorld helloWorld;
	@Bean MethodInvokingTaskletAdapter methodInvokingTaskletAdapter() {
		MethodInvokingTaskletAdapter adapter = new  MethodInvokingTaskletAdapter();
		adapter.setTargetMethod("sayHello");
		adapter.setTargetObject(helloWorld);
		return adapter;
	}
	@Bean @Qualifier("hello-world")
	public Step  helloWorldStep() {
		Step helloWorldStep = stepBuilderFactory.get("hello-world")
				.tasklet(methodInvokingTaskletAdapter()).build();
		return helloWorldStep;

	}

	@Bean @Qualifier("helloWorld")
	public Job helloWorldJob() {
		return jobBuilderFactory.get("hello-world-job").start(helloWorldStep()).build();
	}

}
