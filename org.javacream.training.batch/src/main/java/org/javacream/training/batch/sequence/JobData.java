package org.javacream.training.batch.sequence;

import java.io.Serializable;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@JobScope
@Component
public class JobData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 

}
