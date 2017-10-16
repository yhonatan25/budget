package org.javaconmanzanas.examples.budget.model;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.springframework.util.Assert.hasLength;

public class Budget {
    private final long id;
    private final String name;

    public Budget(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getIncome() {
        return ZERO;
    }

    public BigDecimal getExpenditure() {
        return ZERO;
    }

    public BigDecimal getProfit() {
        return ZERO;
    }

    public boolean equals(final Object object) {
        if (object instanceof Budget) {
            final Budget otherBudget = (Budget) object;
            return this.name.equals(otherBudget.name);
        }

        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public static class Builder {
        private long id;
        private String name;

        private Builder(final String name) {
            this.name = name;
        }

        public static Builder aBudget(final String name) {
            return new Builder(name);
        }

        public Builder id(final long id) {
            this.id = id;
            return this;
        }

        public Budget build() {
            hasLength(this.name, "The name must not be null nor empty.");
            return new Budget(this);
        }

    }
}
