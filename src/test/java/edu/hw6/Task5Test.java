package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Task5Test {
    @Test
    void hackerNewsTopStories() throws IOException {
        Assertions.assertTrue(HackerNews.hackerNewsTopStories().length != 0);
    }

    @Test
    void news() throws IOException, InterruptedException {
        String title = "JDK 21 Release Notes";
        Assertions.assertEquals(title, HackerNews.news(37570037));
    }
}
