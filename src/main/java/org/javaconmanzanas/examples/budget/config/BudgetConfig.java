package org.javaconmanzanas.examples.budget.config;

import org.javaconmanzanas.examples.budget.model.BudgetManager;
import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.repository.IncomeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class BudgetConfig {

    @Bean
    public BudgetRepository getBudgetRepository() {
        return new BudgetRepository(new HashSet<>());
    }

    @Bean
    public IncomeRepository getIncomeRepository(){
        return new IncomeRepository(new HashSet<>());
    }

    @Bean
    public BudgetManager getBudgetManager(final BudgetRepository budgetRepository, final IncomeRepository incomeRepository){
        return new BudgetManager(budgetRepository, incomeRepository);
    }

}
