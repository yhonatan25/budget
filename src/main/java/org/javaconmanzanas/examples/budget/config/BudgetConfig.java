package org.javaconmanzanas.examples.budget.config;

import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BudgetConfig {

    @Bean
    public BudgetRepository getBudgetRepository() {
        return new BudgetRepository();
    }

}
