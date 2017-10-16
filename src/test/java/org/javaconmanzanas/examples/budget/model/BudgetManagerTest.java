package org.javaconmanzanas.examples.budget.model;

import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.repository.IncomeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.javaconmanzanas.examples.budget.model.Budget.Builder.aBudget;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BudgetManagerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private BudgetManager budgetManager;

    @Test
    public void testSaveIncome() throws Exception {
        final Budget budget = aBudget("November 2017").build();
        when(budgetRepository.get(1L)).thenReturn(Optional.of(budget));
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));

        budgetManager.saveIncome(income);

        verify(budgetRepository).get(1L);
        verify(incomeRepository).save(income);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSaveIncomeNotValidIdBudget() throws Exception {
        when(budgetRepository.get(1L)).thenReturn(empty());
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));

        budgetManager.saveIncome(income);
    }
}
