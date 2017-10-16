package org.javaconmanzanas.examples.budget.repository;

import org.javaconmanzanas.examples.budget.exception.DuplicatedIncomeException;
import org.javaconmanzanas.examples.budget.model.Income;

import java.util.Optional;
import java.util.Set;

public class IncomeRepository {
    private final Set<Income> incomeSet;

    public IncomeRepository(final Set<Income> incomeSet){
        this.incomeSet = incomeSet;
    }


    public void save(final Income income) {
        if(incomeSet.contains(income)){
            throw new DuplicatedIncomeException();
        }

        incomeSet.add(income);
    }

    public Optional<Income> get(final long idIncome, final String name) {
        return incomeSet.stream()
                .filter(income -> income.getIdBudget() == idIncome && income.getName().equals(name))
                .findFirst();
    }
}
