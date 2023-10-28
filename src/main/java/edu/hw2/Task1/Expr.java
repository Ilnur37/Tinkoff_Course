package edu.hw2.Task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public sealed interface Expr
    permits Constant, Negate, Exponent, Addition, Multiplication {

    Logger LOGGER = LogManager.getLogger();

    double evaluate();
}
