package org.javaconmanzanas.examples.budget.rest;

public class BudgetResponse {

    private final long id;

    private final String name;

    public BudgetResponse(final long id, final String name) {
        this.id = id;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
