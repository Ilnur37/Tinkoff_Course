package edu.hw2.Task4;

public record CallingInfo(String className, String methodName) {
    static CallingInfo callingInfo() {
        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();

        return new CallingInfo(methods[2].getClassName(), methods[2].toString());
    }
}
