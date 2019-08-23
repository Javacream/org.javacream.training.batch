package org.javacream.training.batch.spring.simple;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SimpleJobUtilities {
	@Autowired private DataSource dataSource;
	@Autowired private JobOperator jobOperator;
	@Autowired private JobExplorer jobExplorer;
	@Autowired private JdbcTemplate jdbcTemplate;
	@Bean
	public JobExplorer getJobExplorer() throws Exception {
	        JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
	        factoryBean.setDataSource(this.dataSource);
	        factoryBean.setJdbcOperations(jdbcTemplate);
	        return factoryBean.getObject();
	}
	@Bean public JobOperator jobOperator(JobExplorer jobExplorer, JobLauncher jobLauncher, JobRegistry jobRegistry, JobRepository jobRepository) {
		SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
		simpleJobOperator.setJobExplorer(jobExplorer);
		simpleJobOperator.setJobLauncher(jobLauncher);
		simpleJobOperator.setJobRegistry(jobRegistry);
		simpleJobOperator.setJobRepository(jobRepository);
		return simpleJobOperator;
	}
	
	@PostConstruct public void init() {
		try {
			System.out.println("*****************" + jobOperator.getJobNames());
			System.out.println("*****************" + jobExplorer.getJobInstanceCount("job1"));
			System.out.println("*****************" + jobExplorer.getJobInstances("job1", 0, 100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
