/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
