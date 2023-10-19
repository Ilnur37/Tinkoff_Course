package edu.hw2.Task1;

public record Exponent(Expr multiplier, Expr degree) implements Expr {
    public Exponent {
        LOGGER.info("Exponent constructor. \t multiplier is: " + multiplier + "\t\t" + "\t degree is: " + degree);
    }

    @Override
    public double evaluate() {
        if (multiplier.evaluate() < 0 && degree.evaluate() % 1 != 0) {
            throw new IllegalArgumentException("Raising a negative number to a fractional power is impossible!");
        }
        return Math.pow(multiplier.evaluate(), degree.evaluate());
    }
}
