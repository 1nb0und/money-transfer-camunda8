package com.mm.validator;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidatorApplication.class, args);
	}

	@JobWorker(type = "validator")
	public Map<String, Object> orchestrateSomething(final ActivatedJob job) {

		// Do the business logic
		System.out.println("validator called: " + job.getVariables());

		// Probably add some process variables
		HashMap<String, Object> variables = new HashMap<>();

		// put existing vars from other microservices
		variables.putAll(job.getVariablesAsMap());

		// put new vars
		variables.put("validated", true);

		return variables;
	}
}
