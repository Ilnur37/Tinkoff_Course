package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public final class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> DICTIONARY_RESPONSE = new HashMap();
    private static final String DEFAULT_RESPONSE = "Цитата: Я не знаю, что ответить.";
    private static final String END_OF_PROGRAM = "END";
    private static final int BYTE_CAPACITY = 1024;
    private static final int MAX_CONNECTIONS = 1;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    private final int port;

    static {
        DICTIONARY_RESPONSE.put("личности", "Не переходи на личности там, где их нет");
        DICTIONARY_RESPONSE.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
        DICTIONARY_RESPONSE.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        DICTIONARY_RESPONSE.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
    }

    @SneakyThrows(IOException.class)
    public void start() {
        ServerSocket serverSocket = new ServerSocket(port);
        LOGGER.info(String.format("Сервер запущен на порте %d. Ожидание подключений...", port));
        while (true) {
            executorService.submit(new ClientHandler(serverSocket.accept()));
        }
    }

    private record ClientHandler(Socket clientSocket) implements Runnable {
        public void run() {
            try (InputStream inputStream = clientSocket.getInputStream();
                 OutputStream outputStream = clientSocket.getOutputStream()) {
                Server.LOGGER.info(String.format("Подключен клиент: %s", clientSocket));
                outputStream.write("200".getBytes());

                while (true) {
                    String request = getRequest(inputStream);
                    Server.LOGGER.info("Пришло сообщение: " + request);
                    if (request.equals(END_OF_PROGRAM)) {
                        Server.LOGGER.info("Отключен клиент: " + clientSocket);
                        break;
                    }

                    String response = Server.DICTIONARY_RESPONSE.getOrDefault(
                        request.toLowerCase(),
                        DEFAULT_RESPONSE
                    );
                    outputStream.write(response.getBytes());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static String getRequest(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[BYTE_CAPACITY];
            int bytesRead = inputStream.read(buffer);
            return new String(buffer, 0, bytesRead);
        }
    }
}
