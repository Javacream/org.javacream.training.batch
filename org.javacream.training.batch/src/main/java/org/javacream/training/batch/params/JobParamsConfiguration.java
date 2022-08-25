package org.javacream.training.batch.params;

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

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class JobParamsConfiguration {

    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired JobParamTasklet jobParamTasklet;
    @Bean public Step jobParamStep1(){
        return stepBuilderFactory.get("step1").tasklet(jobParamTasklet).build();
    }
    @Bean
    @Qualifier("jobParams")
    public Job jobParams(){
        return jobBuilderFactory.get("job-params-job").start(jobParamStep1()).build();
    }


}
