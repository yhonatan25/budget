package org.javaconmanzanas.examples.budget.exception;

public class DuplicatedBudgetException extends RuntimeException {

    public DuplicatedBudgetException(){
        super("Duplicated budget name.");
    }

}
