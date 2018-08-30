package org.javacream.training.batch.spring.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BusinessConfig {

	@Bean public DataAccessSimulator dataAccessSimulator() {
		return new DataAccessSimulator();
	}
	@Bean @Scope("prototype") public Business2 business2() {
		Business2 b2 = new Business2();
		b2.setSimulator(dataAccessSimulator());
		return b2;
	}
	@Bean public Business1 business1() {
		Business1 b1 = new Business1();
		b1.setSimulator(dataAccessSimulator());
		return b1;
	}
}
