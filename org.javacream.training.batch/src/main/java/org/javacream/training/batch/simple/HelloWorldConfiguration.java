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
    public Step step4(){
        return stepBuilderFactory.get("step4").tasklet(helloWorldTasklet).build();
    }

    @Bean
    @Qualifier("helloWorld")
    public Job helloWorldJob(){
        return jobBuilderFactory.get("hello-world-job").start(step1()).next(step2()).next(step3()).next(step4()).build();
    }
}
