package edu.hw2.Task1;

public record Constant(double value) implements Expr {
    public Constant {
        LOGGER.info("Constant constructor. \t Value is: " + value);
    }

    @Override
    public double evaluate() {
        return value;
    }
}
