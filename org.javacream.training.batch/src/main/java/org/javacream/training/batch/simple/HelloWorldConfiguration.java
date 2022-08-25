package org.javacream.training.batch.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldConfiguration {
    @Autowired StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").tasklet((contribution, context) -> {System.out.println("Hello from Step1"); return RepeatStatus.FINISHED;}).build();
    }
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2").tasklet((contribution, context) -> {System.out.println("Hello from Step2"); return RepeatStatus.FINISHED;}).build();
    }
    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3").tasklet((contribution, context) -> {System.out.println("Hello from Step3"); return RepeatStatus.FINISHED;}).build();
    }

    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("hello-world-job-with-incrementer").incrementer(new RunIdIncrementer()).start(step1()).next(step2()).next(step3()).build();
    }
}