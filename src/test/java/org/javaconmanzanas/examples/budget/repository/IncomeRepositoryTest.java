package org.javaconmanzanas.examples.budget.repository;

import org.javaconmanzanas.examples.budget.exception.DuplicatedIncomeException;
import org.javaconmanzanas.examples.budget.model.Income;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IncomeRepositoryTest {

    private IncomeRepository incomeRepository;

    @Before
    public void setUp() throws Exception {
        incomeRepository =  new IncomeRepository(new HashSet<>());
    }

    @Test
    public void testSaveIncome() throws Exception {
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));

        incomeRepository.save(income);

        final Optional<Income> savedIncome = incomeRepository.get(1L, "Salary");
        assertThat(savedIncome.get(), is(income));
    }

    @Test(expected = DuplicatedIncomeException.class)
    public void testSaveIncomeDoesNotAllowDuplicatedIncomes() throws Exception {
        final Income income = new Income(1L, "Salary", BigDecimal.valueOf(3000.00));
        final Income duplicatedIncome = new Income(1L, "Salary", BigDecimal.valueOf(4000.00));
        incomeRepository.save(income);

        incomeRepository.save(duplicatedIncome);
    }


}
