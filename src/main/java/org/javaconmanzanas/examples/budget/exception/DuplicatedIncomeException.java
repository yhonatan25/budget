package org.javaconmanzanas.examples.budget.exception;

public class DuplicatedIncomeException extends RuntimeException {

    public DuplicatedIncomeException(){
        super("Duplicated income name.");
    }

}
