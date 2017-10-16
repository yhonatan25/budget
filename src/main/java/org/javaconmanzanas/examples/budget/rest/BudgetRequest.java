package org.javaconmanzanas.examples.budget.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class BudgetRequest {
    @NotEmpty
    private final String name;

    public BudgetRequest(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
