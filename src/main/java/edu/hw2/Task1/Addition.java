package edu.hw2.Task1;

public record Addition(Expr firstTerm, Expr secondTerm) implements Expr {
    public Addition {
        LOGGER.info("Addition constructor. \t firstTerm is: " + firstTerm + "\t\t" + "\t secondTerm is: " + secondTerm);
    }

    @Override
    public double evaluate() {
        return firstTerm.evaluate() + secondTerm().evaluate();
    }
}
