package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@PostConstruct public void init() {
		System.out.println("******************");
	}
	
	@Bean @Qualifier("var1") @Scope("singleton") public SimpleBusinessClass simpleBusinessClassVariant1(@Value ("${var1}") String var1, SimpleBusinessComponent simpleBusinessComponent) {
		System.out.println("################   retrieving " + simpleBusinessComponent);
		SimpleBusinessClass instance = new SimpleBusinessClass(var1);
		return instance;
	}
	@Bean @Qualifier("var2") public SimpleBusinessClass simpleBusinessClassVariant2(@Value ("${var2}") String var2) {
		SimpleBusinessClass instance = new SimpleBusinessClass(var2);
		return instance;
	}
}
