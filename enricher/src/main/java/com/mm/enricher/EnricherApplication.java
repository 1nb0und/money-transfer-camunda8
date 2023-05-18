package com.mm.enricher;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EnricherApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnricherApplication.class, args);
	}

	@JobWorker(type = "enricher")
	public Map<String, Object> orchestrateSomething(final ActivatedJob job) {

		// Do the business logic
		System.out.println("enricher called: " + job.getVariables());

		// Probably add some process variables
		HashMap<String, Object> variables = new HashMap<>();

		// put existing vars from other microservices
		variables.putAll(job.getVariablesAsMap());

		// put new vars
		variables.put("enriched", true);
		variables.put("customerName", "Ada Lovelace");

		return variables;
	}
}
