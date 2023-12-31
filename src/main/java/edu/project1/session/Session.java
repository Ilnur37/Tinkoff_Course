package edu.project1.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Session {

    private final String answer;
    private final int maxAttempts;
    private Set<Character> differentLettersInAnswer = new HashSet<>();
    private List<Character> userAnswers = new ArrayList<>();
    private int attempts;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        addDifferentLettersInAnswer(this.answer);
    }

    //Constructor for tests
    public Session(
        String answer,
        int maxAttempts,
        Set<Character> differentLettersInAnswer,
        List<Character> userAnswers,
        int attempts
    ) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        this.differentLettersInAnswer = differentLettersInAnswer;
        this.userAnswers = userAnswers;
        this.attempts = attempts;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public Set<Character> getDifferentLettersInAnswer() {
        return differentLettersInAnswer;
    }

    public List<Character> getUserAnswers() {
        return userAnswers;
    }

    public int getAttempts() {
        return attempts;
    }

    public void addAnswer(char answer) {
        this.userAnswers.add(answer);
    }

    public void incrementAttempts() {
        ++this.attempts;
    }

    public void removeCharFromAns(char ch) {
        differentLettersInAnswer.remove(ch);
    }

    private void addDifferentLettersInAnswer(String string) {
        for (char ch : string.toCharArray()) {
            this.differentLettersInAnswer.add(ch);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Session o = (Session) obj;
        return Objects.equals(answer, o.answer)
            && maxAttempts == o.maxAttempts
            && differentLettersInAnswer.equals(o.differentLettersInAnswer)
            && userAnswers.equals(o.userAnswers)
            && attempts == o.attempts;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
