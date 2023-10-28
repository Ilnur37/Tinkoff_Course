package edu.project1.difficulty;

import java.util.List;

public class DifficultyManager {
    private static final int MIN_LENGTH = 2;
    private static final Dictionary DICTIONARY = new Dictionary();

    public int getMaxDifficulty() {
        return DICTIONARY.getMaxDifficulty();
    }

    public int getMaxAttempt(int level) {
        return DICTIONARY.getMaxAttempts(level - 1);
    }

    public String getRandomWord(int level) {
        List<String> listOfWords = DICTIONARY.getListOfWords(level - 1);
        int firstIdx = (int) (Math.random() * listOfWords.size());
        int idx = getIdxValidWord(firstIdx, listOfWords);
        if (idx == -1) {
            return "-1";
        } else {
            return listOfWords.get(idx);
        }
    }

    private int getIdxValidWord(int firstIdx, List<String> listOfWords) {
        int idx = firstIdx;
        while (listOfWords.get(idx).length() < MIN_LENGTH) {
            idx = (idx + 1) % listOfWords.size();
            if (idx == firstIdx) {
                return -1;
            }
        }
        return idx;
    }
}
