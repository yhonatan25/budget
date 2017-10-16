package org.javaconmanzanas.examples.budget.controller;

import org.javaconmanzanas.examples.budget.repository.Budget;
import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.rest.BudgetRequest;
import org.javaconmanzanas.examples.budget.rest.BudgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.javaconmanzanas.examples.budget.repository.Budget.Builder.aBudget;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BudgetResponse saveBudget(@Valid @RequestBody final BudgetRequest budgetRequest) {
        final Budget budget = aBudget(budgetRequest.getName()).build();
        final Optional<Budget> budgetOptional = budgetRepository.save(budget);
        final Budget savedBudget = budgetOptional.get();
        return new BudgetResponse(savedBudget.getId(), savedBudget.getName());
    }


}
