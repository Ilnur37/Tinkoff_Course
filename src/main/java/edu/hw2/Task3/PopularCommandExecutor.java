package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.manager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts = 5;

    public PopularCommandExecutor(ConnectionManager manager) {
        this.manager = manager;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        boolean commandCompleted = false;
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                commandCompleted = true;
                break;
            } catch (Exception e) {
                throw new ConnectionException(e.getMessage());
            }
        }
        if (!commandCompleted) {
            throw new ConnectionException("");
        }
    }
}
