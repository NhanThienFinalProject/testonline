package com.testonline.service.impl;

import com.testonline.entity.AnswerEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.repository.QuestionRepository;
import com.testonline.service.IQuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    QuestionRepository questionRP;
    @Autowired
    AnswerService answerSV;
    @Autowired
    QuestionOfExamtitleService questionOfExamtitleSV;

    @Override
    public List<QuestionEntity> findAll() {
        return (List<QuestionEntity>) questionRP.findAll();
    }

    @Override
    public List<QuestionEntity> findQuestionByUserId(int id) {
        return questionRP.findQuestionByUserId(id);
    }

    @Override
    public QuestionEntity findQuestionByQuestionId(int questionId) {
        return questionRP.findByQuestionId(questionId);
    }

    @Override
    public QuestionEntity saveQuestion(QuestionEntity question) {
        return questionRP.save(question);
    }

    @Override
    public void saveFullQuestion(QuestionEntity question, String[] anotherAnswers, String correctAnswer) {
        QuestionEntity missingQuestion = saveQuestion(question);
//        Add correctAnswer to array and random
        Random rd = new Random();
        int randomIndex = rd.nextInt(anotherAnswers.length + 1);
        String[] listAnswers = new String[anotherAnswers.length + 1];
        for (int i = 0; i < listAnswers.length; i++) {
            if (i < randomIndex) {
                listAnswers[i] = anotherAnswers[i];
            } else if (i == randomIndex) {
                listAnswers[i] = correctAnswer;
            } else {
                listAnswers[i] = anotherAnswers[i - 1];
            }
        }
//        Save answer to DB and get correctAnswerInstance
        AnswerEntity correctAnswerInstance = new AnswerEntity();
        for (int i = 0; i < listAnswers.length; i++) {
            AnswerEntity answerInstance = new AnswerEntity();
            answerInstance.setAnswer(listAnswers[i]);
            answerInstance.setQuestion(missingQuestion);
            if (i == randomIndex) {
                correctAnswerInstance = answerSV.saveAnswer(answerInstance);
            } else {
                answerSV.saveAnswer(answerInstance);
            }
        }
//        get correctAnswerID to save back to missingQuesion and save to DB
        int correctAnswerId = correctAnswerInstance.getAnswerId();
        missingQuestion.setCorrectAnswerId(correctAnswerId);
        QuestionEntity fullQuesion = saveQuestion(missingQuestion);
    }

    public String[] deleteNullElement(String[] anotherAnswer) {
        int newLength = 0;
        for (String s : anotherAnswer) {
            if (!(s.equals("") || s == null)) {
                newLength++;
            }
        }
        String[] anotherAnswerWithoutNull = new String[newLength];
        int index = 0;
        for (int i = 0; i < anotherAnswer.length; i++) {
            if (!(anotherAnswer[i].equals("") || anotherAnswer[i] == null)) {
                anotherAnswerWithoutNull[index] = anotherAnswer[i];
                index++;
            }
        }
        return anotherAnswerWithoutNull;
    }

    @Override
    public List<QuestionEntity> findQuestionByCategoryId(int categoryId) {
        return questionRP.findQuestionByCategoryId(categoryId);
    }

    @Override
    public List<QuestionEntity> findQuestionByCategoryIdAndExamIdAndNotInQuestionRandom(int categoryId, int examId) {
        return questionRP.findQuestionByCategoryIdAndExamIdAndNotInQuestionRandom(categoryId, examId);
    
    }
    @Override
    public List<QuestionEntity> getListQuestionByExamtitleId(int examtitleId) {
        List<QuestionOfExamtitleEntity> listQuestionOfExamtitle = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId);
        List<QuestionEntity> listQuestionInExamtitle = new ArrayList<QuestionEntity>();
        for (QuestionOfExamtitleEntity qoe : listQuestionOfExamtitle) {
            QuestionEntity question = qoe.getQuestion();
            listQuestionInExamtitle.add(question);
        }
        return listQuestionInExamtitle;
    }

    @Override
    public List<QuestionEntity> findQuestionByCategoryId(int categoryId) {
        return questionRP.findQuestionByCategoryId(categoryId);
    }

    @Override
    public List<QuestionEntity> findQuestionByCategoryIdAndExamIdAndNotInQuestionRandom(int categoryId, int examId) {
        return questionRP.findQuestionByCategoryIdAndExamIdAndNotInQuestionRandom(categoryId, examId);
    }
}
