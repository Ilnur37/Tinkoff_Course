package edu.hw10.Task2;

interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
