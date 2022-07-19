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
		message = executionContext.getString("simpleData", "");
		processed = executionContext.getInt("processed", 0);
		
	}

	private String message;
	private int processed;

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
		executionContext.put("processed", processed);
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		executionContext.put("simpleData", message);
	}
}
