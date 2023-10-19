package edu.hw2.Task3.manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnectionImpl;

public class DefaultConnectionManager implements ConnectionManager {
    private int numberOfConnections = 0;

    @Override
    public Connection getConnection() {
        ++numberOfConnections;
        if ((numberOfConnections - 1) % 2 == 0) {
            return new StableConnectionImpl();
        } else {
            return new FaultyConnection();
        }
    }
}
