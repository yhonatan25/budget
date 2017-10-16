package org.javaconmanzanas.examples.budget.model;

import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.repository.IncomeRepository;

import java.util.NoSuchElementException;

public class BudgetManager {
    private final BudgetRepository budgetRepository;
    private final IncomeRepository incomeRepository;

    public BudgetManager(final BudgetRepository budgetRepository, final IncomeRepository incomeRepository) {
        this.budgetRepository = budgetRepository;
        this.incomeRepository = incomeRepository;
    }


    public void saveIncome(final Income income) {
        validateIncome(income);

        incomeRepository.save(income);
    }

    private void validateIncome(Income income) {
        budgetRepository.get(income.getIdBudget())
                .orElseThrow(() -> new NoSuchElementException());
    }
}
