package org.javacream.training.batch.spring.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleJobConfiguration.class)
public class SimpleJobTests {

	@Test
	public void contextLoads() {
	}

}
