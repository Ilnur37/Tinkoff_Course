package edu.project1.UserResult;

import edu.project1.AnswerResult;
import edu.project1.Session;

public sealed interface UserResult permits EndGame, InvalidAnswer, LetterGuessed {

    AnswerResult responseProcessing(Session session, String inputAnswer);
}
