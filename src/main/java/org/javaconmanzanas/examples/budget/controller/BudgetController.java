package org.javaconmanzanas.examples.budget.controller;

import org.javaconmanzanas.examples.budget.model.Budget;
import org.javaconmanzanas.examples.budget.repository.BudgetRepository;
import org.javaconmanzanas.examples.budget.rest.BudgetRequest;
import org.javaconmanzanas.examples.budget.rest.BudgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.javaconmanzanas.examples.budget.model.Budget.Builder.aBudget;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @RequestMapping("/{budgetId}")
    @ResponseStatus(OK)
    @ResponseBody
    public BudgetResponse getBudget(@PathVariable final long budgetId) {
        final Optional<Budget> budgetOptional = budgetRepository.get(budgetId);
        final Budget requestedBudget = budgetOptional.get();
        return new BudgetResponse(requestedBudget.getId(), requestedBudget.getName());
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    @ResponseBody
    public BudgetResponse saveBudget(@Valid @RequestBody final BudgetRequest budgetRequest) {
        final Budget budget = aBudget(budgetRequest.getName()).build();
        final Budget savedBudget = budgetRepository.save(budget);
        return new BudgetResponse(savedBudget.getId(), savedBudget.getName());
    }


}
