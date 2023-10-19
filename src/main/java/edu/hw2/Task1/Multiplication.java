package edu.hw2.Task1;

public record Multiplication(Expr firstMultiplier, Expr secondMultiplier) implements Expr {
    public Multiplication {
        LOGGER.info("Multiplication constructor. \t firstMultiplier is: " + firstMultiplier + "\t\t"
            +  "\t secondMultiplier is: " + secondMultiplier);
    }

    @Override
    public double evaluate() {
        return firstMultiplier.evaluate() * secondMultiplier.evaluate();
    }
}
