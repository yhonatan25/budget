package org.javaconmanzanas.examples.budget.config;

import org.javaconmanzanas.examples.budget.controller.BudgetController;
import org.javaconmanzanas.examples.budget.exception.DuplicatedBudgetException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(assignableTypes = {BudgetController.class})
public class ExceptionHandlerConfig {

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DuplicatedBudgetException.class)
    public void budgetRepeated() {

    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void budgetNotFound(){

    }
}
