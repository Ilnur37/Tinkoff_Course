package edu.project1.UserResult;

import edu.project1.AnswerResult;
import edu.project1.session.Session;

public sealed interface UserResult permits EndGame, InvalidAnswer, LetterGuessed {

    AnswerResult processResponse(Session session, String inputAnswer);
}
