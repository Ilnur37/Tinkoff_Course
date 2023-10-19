package edu.hw2.Task3.connection;

import edu.hw2.Task3.ConnectionException;

public class FaultyConnection implements Connection {
    private int numberOfConnections = 0;

    @Override
    public void execute(String command) throws ConnectionException {
        if (numberOfConnections % 2 == 0) {
            Connection.super.execute(command);
            ++numberOfConnections;
        } else {
            throw new ConnectionException("Problem connection!");
        }
    }

    @Override
    public void close() throws Exception {
        Connection.super.close();
        --numberOfConnections;
    }

}
