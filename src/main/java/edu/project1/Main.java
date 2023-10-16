package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//S T A R T   G A M E
public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {

    }

    //S T A R T   G A M E
    public static void main(String[] args) {
        Console console = new Console();
        console.run();
    }
}
