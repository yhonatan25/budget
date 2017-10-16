package org.javaconmanzanas.examples.budget.model;

import java.math.BigDecimal;

public class Income {
    private final long idBudget;
    private final String name;
    private final BigDecimal amount;

    public Income(final long idBudget, final String name, final BigDecimal amount) {
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


    public boolean equals(final Object object) {
        if (object instanceof Income) {
            final Income otherIncom = (Income) object;
            return this.idBudget == otherIncom.getIdBudget() && this.name.equals(otherIncom.getName());
        }

        return false;
    }

    public int hashCode() {
        return this.name.hashCode() + (int) this.idBudget * 31;
    }
}
