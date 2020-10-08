
package com.testonline.service;

import com.testonline.entity.QuestionEntity;
import java.util.List;


public interface IQuestionService {
    List<QuestionEntity> findAll();
    List<QuestionEntity> findQuestionByUserId(int userId);
    QuestionEntity findQuestionByQuestionId(int questionId);
    QuestionEntity saveQuestion(QuestionEntity question);
    void saveFullQuestion(QuestionEntity question,String[] antherAnswers,String correctAnswer);
    List<QuestionEntity> getListQuestionByExamtitleId(int examtitleId);
}
