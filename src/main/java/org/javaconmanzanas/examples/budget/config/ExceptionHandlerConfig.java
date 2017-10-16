package org.javaconmanzanas.examples.budget.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice(assignableTypes = {BudgetController.class})
public class ExceptionHandlerConfig {

    @ResponseStatus(value = CONFLICT, reason = "Resource already created")
    @ExceptionHandler(NoSuchElementException.class)
    public void budgetRepeated() {

    }


}
