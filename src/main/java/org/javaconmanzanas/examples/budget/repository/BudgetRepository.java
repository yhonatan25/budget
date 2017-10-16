package org.javaconmanzanas.examples.budget.repository;

import org.javaconmanzanas.examples.budget.exception.DuplicatedBudgetException;
import org.javaconmanzanas.examples.budget.model.Budget;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static org.javaconmanzanas.examples.budget.model.Budget.Builder.aBudget;

public class BudgetRepository {

    private final AtomicLong atomicLong;
    private final Set<Budget> budgetSet;

    public BudgetRepository(final Set<Budget> budgetSet) {
        atomicLong = new AtomicLong();
        this.budgetSet = budgetSet;
    }

    public Budget save(final Budget budget) {
        validateDuplicatedBudget(budget);
        final long budgetId = atomicLong.incrementAndGet();
        final Budget budgetToSave = aBudget(budget.getName()).id(budgetId).build();
        budgetSet.add(budgetToSave);
        return budgetToSave;
    }

    private void validateDuplicatedBudget(Budget budget) {
        if (budgetSet.contains(budget)) {
            throw new DuplicatedBudgetException();
        }
    }

    public Optional<Budget> get(final long idBudget) {
        return budgetSet
                .stream()
                .filter(budget -> budget.getId() == idBudget)
                .findFirst();
    }
}
