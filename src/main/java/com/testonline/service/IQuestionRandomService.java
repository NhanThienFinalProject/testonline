
package com.testonline.service;

import com.testonline.entity.QuestionRandomEntity;
import java.util.List;


public interface IQuestionRandomService {
   List<QuestionRandomEntity> getAllByExamIdAndUserId(int examId,int userId);
   void saveQuestionRandom(QuestionRandomEntity questionRandom);
   String checkAndSaveQuestionRandom(String idExam, String[] listQuestionId);
   QuestionRandomEntity getById(int questionRandomId);
   void delete(QuestionRandomEntity questionRandom);
}

