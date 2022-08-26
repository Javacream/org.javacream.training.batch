package org.javacream.training.batch.params;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableBatchProcessing
public class JobParamsConfiguration {

    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    JobParamTasklet1 jobParamTasklet1;
    @Autowired
    JobParamTasklet2 jobParamTasklet2;
    @Bean public Step jobParamStep1(){
        return stepBuilderFactory.get("step1").tasklet(jobParamTasklet1).build();
    }
    @Bean public Step jobParamStep2(){
        return stepBuilderFactory.get("step2").tasklet(jobParamTasklet2).build();
    }
    @Bean
    @Qualifier("jobParams")
    public Job jobParams(){
        return jobBuilderFactory.get("job-params-job").start(jobParamStep1()).next(jobParamStep2()).build();
    }


}
