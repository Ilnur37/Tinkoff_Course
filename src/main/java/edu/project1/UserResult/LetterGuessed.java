package edu.project1.UserResult;

import edu.project1.AnswerResult;
import edu.project1.session.Session;

public final class LetterGuessed implements UserResult {
    @Override
    public AnswerResult processResponse(Session session, String inputAnswer) {
        char inputChar = inputAnswer.charAt(0);

        if (session.getDifferentLettersInAnswer().contains(inputChar)) {
                return AnswerResult.HIT;
        }
        return AnswerResult.NOT_HIT;
    }
}
