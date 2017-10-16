package org.javaconmanzanas.examples.budget.repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javaconmanzanas.examples.budget.repository.Budget.Builder.aBudget;

public class BudgetRepositoryTest {

    private static final String BUDGET_NAME = "November 2017";
    private static final long BUDGET_ID = 1L;

    private BudgetRepository budgetRepository;

    @Before
    public void setUp() throws Exception {
        budgetRepository = new BudgetRepository();
    }

    @Test
    public void testSaveBudget() throws Exception {
        final Budget budget = aBudget(BUDGET_NAME).build();

        final Optional<Budget> savedBudget = budgetRepository.save(budget);

        assertThatBudgetIs(savedBudget.get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveBudgetDoesNotAllowNullName() {
        final Budget budget = aBudget(null).build();

        final Optional<Budget> savedBudget = budgetRepository.save(budget);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveBudgetDoesNotAllowEmptyName() {
        final Budget budget = aBudget("").build();

        final Optional<Budget> savedBudget = budgetRepository.save(budget);
    }

    @Test
    public void testSaveBudgetDoesNotAllowRepeated() {
        final Budget firstBudget = aBudget(BUDGET_NAME).build();
        final Budget secondBudget = aBudget(BUDGET_NAME).build();

        final Optional<Budget> firstSavedBudget = budgetRepository.save(firstBudget);
        final Optional<Budget> secondSavedBudget = budgetRepository.save(secondBudget);

        assertThatBudgetIs(firstSavedBudget.get());
        assertThat(secondSavedBudget.isPresent(), is(FALSE));
    }

    private void assertThatBudgetIs(final Budget expectedBudget) {
        assertThat(expectedBudget.getId(), is(BUDGET_ID));
        assertThat(expectedBudget.getName(), is(BUDGET_NAME));
        assertThat(expectedBudget.getIncome(), is(ZERO));
        assertThat(expectedBudget.getExpenditure(), is(ZERO));
        assertThat(expectedBudget.getProfit(), is(ZERO));
    }


}
