package org.javacream.training.batch.sequence;

import java.io.Serializable;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class JobData implements Serializable{

	private static final long serialVersionUID = 1L;

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
