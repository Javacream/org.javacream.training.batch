package org.javacream.training.batch.web;

import java.util.Objects;
import java.util.Properties;

public class JobLaunchRequest {

	private String jobName;
	private Properties jobParameters;
	@Override
	public String toString() {
		return "JobLaunchRequest [jobName=" + jobName + ", jobParameters=" + jobParameters + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(jobName, jobParameters);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobLaunchRequest other = (JobLaunchRequest) obj;
		return Objects.equals(jobName, other.jobName) && Objects.equals(jobParameters, other.jobParameters);
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Properties getJobParameters() {
		return jobParameters;
	}
	public void setJobParameters(Properties jobParameters) {
		this.jobParameters = jobParameters;
	}
}