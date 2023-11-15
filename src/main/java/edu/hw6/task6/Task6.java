package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int START_PORT = 0;
    private final static int END_PORT = 49151;
    private final static int PERIOD =  1000;

    private Task6() {

    }

    public static void scanPorts() {
        int emptyPort = 1;
        StringBuilder str = new StringBuilder();
        LOGGER.info("Scanning ports...");

        for (int port = START_PORT; port <= END_PORT; port++) {
            str.append("Port ");
            if (isPortAvailable(port)) {
                emptyPort++;
                if (emptyPort % PERIOD == 0) {
                    str.append(port).append(" is free");
                    LOGGER.info(str.toString());
                    emptyPort = 1;
                }

            } else {
                str.append(port).append(" is occupied ").append(getServiceName(port));
                LOGGER.info(str.toString());
            }
            str.setLength(0);
        }
    }

    private static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);
             DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static String getServiceName(int port) {
        if (PortsService.MAP.get(port) == null) {
            return "";
        } else {
            return " : " + PortsService.MAP.get(port);
        }
    }
}
