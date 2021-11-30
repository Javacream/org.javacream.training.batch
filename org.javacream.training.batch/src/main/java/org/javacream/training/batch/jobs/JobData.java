package org.javacream.training.batch.jobs;

import java.util.Objects;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class JobData {
	private String message;
	private String name;
	@Override
	public String toString() {
		return "JobData [message=" + message + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(message, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobData other = (JobData) obj;
		return Objects.equals(message, other.message) && Objects.equals(name, other.name);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
