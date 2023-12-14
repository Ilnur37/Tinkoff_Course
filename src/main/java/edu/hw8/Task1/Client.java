package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class Client {
    private final String serverAddress;
    private final int serverPort;
    private static final String END_OF_PROGRAM = "END";
    private static final int BYTE_CAPACITY = 1024;
    private static final Logger LOGGER = LogManager.getLogger();

    public void start() {
        try (Socket socket = new Socket(this.serverAddress, this.serverPort)) {

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            LOGGER.info("Ожидайте подключения");
            String codeOfConnection = getResponse(inputStream);
            if (!codeOfConnection.equals("200")) {
                LOGGER.info("Подколючение невозможно");
                throw new RuntimeException("Server returned unknown value!");
            }

            LOGGER.info("Соединение установлено. Для отключения введитe 'END'");

            while (true) {
                LOGGER.info("Введите ключевое слово: ");
                Scanner scanner = new Scanner(System.in);
                String keyword = scanner.nextLine();
                outputStream.write(keyword.getBytes());
                if (keyword.equals(END_OF_PROGRAM)) {
                    break;
                }

                String response = getResponse(inputStream);
                LOGGER.info("Ответ от сервера: " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[BYTE_CAPACITY];
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}
