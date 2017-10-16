package org.javaconmanzanas.examples.budget.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class IncomeRequest {

    private final long idBudget;
    private final String name;
    private final BigDecimal amount;

    public IncomeRequest(@JsonProperty("idBudget") final long idBudget, @JsonProperty("name") final String name, @JsonProperty("amount") final BigDecimal amount) {
        this.idBudget = idBudget;
        this.name = name;
        this.amount = amount;
    }

    public long getIdBudget() {
        return idBudget;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
