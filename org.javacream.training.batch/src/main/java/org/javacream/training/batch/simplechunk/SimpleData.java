package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SimpleData {

	private ExecutionContext executionContext;
	public void setExecutionContext(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		executionContext.put("simpleData", message);
	}
}
