package edu.hw2.Task1;

public record Negate(Expr constant) implements Expr {
    public Negate(Expr constant) {
        LOGGER.info("Negate constructor. \t constant is: " + constant);
        double constVal = constant.evaluate();
        this.constant = new Constant(0 - constVal);
    }

    @Override
    public double evaluate() {
        return constant.evaluate();
    }
}
