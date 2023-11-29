package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String serverAddress;
    private final int serverPort;
    private final String endOfProgram = "END";
    private final int byteCapacity = 1024;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void main(String[] args) {
        this.start();
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void start() {
        try (Socket socket = new Socket(this.serverAddress, this.serverPort)) {

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            System.out.println("Ожидайте подключения");
            String codeOfConnection = this.getResponse(inputStream);
            if (!codeOfConnection.equals("200")) {
                return;
            }

            System.out.println("Соединение установлено. Для отключения введитe 'END'");

            while (true) {
                System.out.println("Введите ключевое слово: ");
                Scanner scanner = new Scanner(System.in);
                String keyword = scanner.nextLine();
                outputStream.write(keyword.getBytes());
                if (keyword.equals(endOfProgram)) {
                    break;
                }

                String response = this.getResponse(inputStream);
                System.out.println("Ответ от сервера: " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[byteCapacity];
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}
