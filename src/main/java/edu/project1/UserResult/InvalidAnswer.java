package edu.project1.UserResult;

import edu.project1.AnswerResult;
import edu.project1.session.Session;

public final class InvalidAnswer implements UserResult {
    @Override
    public AnswerResult processResponse(Session session, String inputAnswer) {
        if (inputAnswer.length() != 1) {
            return AnswerResult.INVALID_ANSWER_LENGTH;
        }

        char inputChar = inputAnswer.charAt(0);
        if (inputChar < 'а' || inputChar > 'я') {
            return AnswerResult.INVALID_ANSWER_CHAR;
        }

        for (char ch : session.getUserAnswers()) {
            if (inputChar == ch) {
                return AnswerResult.INVALID_ANSWER_REPEAT_CHAR;
            }
        }

        return AnswerResult.VALID_ANSWER;
    }
}
