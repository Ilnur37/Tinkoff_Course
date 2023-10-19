package edu.hw2.Task3.connection;

public class StableConnectionImpl implements Connection {
    int numberOfConnections = 0;

    @Override
    public void execute(String command) {
        Connection.super.execute(command);
        ++numberOfConnections;
    }

    @Override
    public void close() throws Exception {
        Connection.super.close();
        --numberOfConnections;
    }
}
