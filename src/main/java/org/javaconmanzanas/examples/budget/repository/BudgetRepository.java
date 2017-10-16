package org.javaconmanzanas.examples.budget.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Optional.empty;
import static org.javaconmanzanas.examples.budget.repository.Budget.Builder.aBudget;

public class BudgetRepository {

    private final AtomicLong atomicLong;
    private final Set<Budget> budgetSet;

    public BudgetRepository() {
        atomicLong = new AtomicLong();
        budgetSet = new HashSet<>();
    }

    public Optional<Budget> save(final Budget budget) {
        final boolean duplicatedBudget = budgetSet.contains(budget);
        if (!duplicatedBudget) {
            final long budgetId = atomicLong.incrementAndGet();
            final Budget budgetToSave = aBudget(budget.getName()).id(budgetId).build();
            budgetSet.add(budgetToSave);
            return Optional.of(budgetToSave);
        }
        return empty();
    }
}
