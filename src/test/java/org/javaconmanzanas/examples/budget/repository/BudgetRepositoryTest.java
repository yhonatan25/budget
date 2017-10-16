package org.javaconmanzanas.examples.budget.repository;

import org.javaconmanzanas.examples.budget.exception.DuplicatedBudgetException;
import org.javaconmanzanas.examples.budget.model.Budget;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javaconmanzanas.examples.budget.model.Budget.Builder.aBudget;

public class BudgetRepositoryTest {

    private static final String BUDGET_NAME = "November 2017";
    private static final long BUDGET_ID = 1L;

    private BudgetRepository budgetRepository;

    @Before
    public void setUp() throws Exception {
        budgetRepository = new BudgetRepository(new HashSet<>());
    }

    @Test
    public void testSaveBudget() throws Exception {
        final Budget budget = aBudget(BUDGET_NAME).build();

        final Budget savedBudget = budgetRepository.save(budget);

        assertThatExpectedDataMatches(savedBudget);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveBudgetDoesNotAllowNullName() {
        final Budget budget = aBudget(null).build();

        final Budget savedBudget = budgetRepository.save(budget);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveBudgetDoesNotAllowEmptyName() {
        final Budget budget = aBudget("").build();

        final Budget savedBudget = budgetRepository.save(budget);
    }

    @Test(expected = DuplicatedBudgetException.class)
    public void testSaveBudgetDoesNotAllowRepeated() {
        final Budget firstBudget = aBudget(BUDGET_NAME).build();
        final Budget secondBudget = aBudget(BUDGET_NAME).build();
        budgetRepository.save(firstBudget);

        budgetRepository.save(secondBudget);
    }

    @Test
    public void testGetBudget() throws Exception {
        final Budget budget = aBudget(BUDGET_NAME).build();
        final Budget savedBudget = budgetRepository.save(budget);

        final Optional<Budget> requestedBudget = budgetRepository.get(1L);

        assertThat(requestedBudget.get(), is(savedBudget));
    }

    @Test
    public void testGetBudgetNotFound() throws Exception {
        final Optional<Budget> requestedBudget = budgetRepository.get(1L);

        assertThat(requestedBudget.isPresent(), is(FALSE));
    }

    private void assertThatExpectedDataMatches(final Budget budget) {
        assertThat(budget.getId(), is(BUDGET_ID));
        assertThat(budget.getName(), is(BUDGET_NAME));
        assertThat(budget.getIncome(), is(ZERO));
        assertThat(budget.getExpenditure(), is(ZERO));
        assertThat(budget.getProfit(), is(ZERO));
    }


}
