package org.javacream.training.batch.spring.simple;

import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;

public class SimpleExceptionHandler implements ExceptionHandler {

	@Override
	public void handleException(RepeatContext context, Throwable throwable) throws Throwable {
		//context.
	}

}
