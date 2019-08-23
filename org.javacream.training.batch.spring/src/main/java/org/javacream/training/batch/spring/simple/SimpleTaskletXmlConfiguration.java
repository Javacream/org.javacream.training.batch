package org.javacream.training.batch.spring.simple;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@EnableBatchProcessing
//@Configuration
@ImportResource("classpath:simple-chunk-job.xml")
public class SimpleTaskletXmlConfiguration {

}
