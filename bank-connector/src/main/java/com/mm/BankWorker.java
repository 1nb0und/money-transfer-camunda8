package com.mm;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.quarkiverse.zeebe.JobWorker;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BankWorker {
    @JobWorker(type = "bank-connector")
    public Map<String, Object> orchestrateSomething(final ActivatedJob job) {

        // Do the business logic
        System.out.println("bank-connector called: " + job.getVariables());

        // Probably add some process variables
        HashMap<String, Object> variables = new HashMap<>();

        // put existing vars from other microservices
        variables.putAll(job.getVariablesAsMap());

        // put new vars
        variables.put("bank-routed", true);
        variables.put("network", "US:ACH");

        return variables;
    }
}
