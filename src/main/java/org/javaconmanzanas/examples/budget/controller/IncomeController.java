package org.javaconmanzanas.examples.budget.controller;

import org.javaconmanzanas.examples.budget.model.BudgetManager;
import org.javaconmanzanas.examples.budget.model.Income;
import org.javaconmanzanas.examples.budget.rest.IncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private BudgetManager budgetManager;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void saveIncome(@RequestBody final IncomeRequest incomeRequest){
        final Income income = new Income(incomeRequest.getIdBudget(), incomeRequest.getName(), incomeRequest.getAmount());
        budgetManager.saveIncome(income);
    }

}
