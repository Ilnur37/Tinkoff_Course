package edu.project1.UserResult;

import edu.project1.AnswerResult;
import edu.project1.session.Session;

public final class EndGame implements UserResult {
    @Override
    public AnswerResult processResponse(Session session, String inputAnswer) {
        if (0 == session.getDifferentLettersInAnswer().size()) {
            return AnswerResult.WIN;
        }
        if (session.getAttempts() >= session.getMaxAttempts()) {
            return AnswerResult.DEFEAT;
        }
        return AnswerResult.NEXT;
    }
}
