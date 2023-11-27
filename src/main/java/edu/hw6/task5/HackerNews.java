package edu.hw6.task5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final URI TOP_NEWS_URI = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json");
    private static final String NEWS_URI = "https://hacker-news.firebaseio.com/v0/item/";
    private static final String TITLE_PARSER = "\"title\":\"(.*?)\"";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final int START_IDX = 9;

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() throws JsonProcessingException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(TOP_NEWS_URI)
            .build();
        HttpResponse<String> response;
        try {
            response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
        List<IdentifierNews> identifierNews = OBJECT_MAPPER.readValue(response.body(), new TypeReference<>() {
        });

        return identifierNews.stream()
            .mapToLong(IdentifierNews::getIdentifier)
            .toArray();
    }

    public static String news(long id) throws IOException, InterruptedException {
        URI newsURi = URI.create(NEWS_URI + id + ".json");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(newsURi)
            .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        String responseStr = response.body();

        Pattern pattern = Pattern.compile(TITLE_PARSER);
        Matcher matcher = pattern.matcher(responseStr);
        if (!matcher.find()) {
            return "";
        }
        String tempTitle = matcher.group();
        return tempTitle.substring(START_IDX, tempTitle.length() - 1);
    }
}
