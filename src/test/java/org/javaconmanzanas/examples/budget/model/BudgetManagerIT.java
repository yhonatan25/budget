package org.javaconmanzanas.examples.budget.model;

import org.javaconmanzanas.examples.budget.config.BudgetConfig;
import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.repository.IncomeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.javaconmanzanas.examples.budget.model.Budget.Builder.aBudget;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BudgetConfig.class})
public class BudgetManagerIT {

    @Autowired
    private BudgetManager budgetManager;

    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    public void testSaveIncome() throws Exception {
        final Budget budget = aBudget("November 2017").build();
        budgetRepository.save(budget);
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));

        budgetManager.saveIncome(income);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSaveIncomeNotValidIdBudget() throws Exception {
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));

        budgetManager.saveIncome(income);
    }
}
