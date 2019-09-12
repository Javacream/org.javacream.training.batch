package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@PostConstruct public void init() {
		System.out.println("******************");
	}
	
	@Bean @Qualifier("var1") public SimpleBusinessClass simpleBusinessClassVariant1() {
		SimpleBusinessClass instance = new SimpleBusinessClass("Variant1");
		return instance;
	}
	@Bean @Qualifier("var2") public SimpleBusinessClass simpleBusinessClassVariant2() {
		SimpleBusinessClass instance = new SimpleBusinessClass("Variant2");
		return instance;
	}
}
