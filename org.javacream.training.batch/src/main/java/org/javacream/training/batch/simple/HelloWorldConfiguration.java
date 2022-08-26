package org.javacream.training.batch.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldConfiguration {
    @Autowired StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired private HelloWorldTasklet helloWorldTasklet;
    @Autowired private Step1Tasklet step1Tasklet;
    @Autowired private Step2Tasklet step2Tasklet;
    @Autowired private Step3Tasklet step3Tasklet;
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").tasklet(step1Tasklet).build();
    }
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2").tasklet(step2Tasklet).build();
    }
    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3").tasklet(step3Tasklet).build();
    }
    @Bean
    public Step step4(){
        return stepBuilderFactory.get("step4").tasklet(helloWorldTasklet).build();
    }

    @Bean
    @Qualifier("helloWorld")
    public Job helloWorldJob(){
        return jobBuilderFactory.get("hello-world-job").start(step1()).on("HUGO").to(step2()).from(step1()).on("EMIL").to(step3()).end().build();
    }
}
