package com.testonline.service.impl;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.repository.ExamtitleRepository;
import com.testonline.service.IExamtitleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamtitleService implements IExamtitleService {

    @Autowired
    private QuestionOfExamtitleService questionOfExamtitleSV;
    @Autowired
    QuestionService questionSV;
    @Autowired
    private ExamtitleRepository examtitleRP;
    @Autowired
    QuestionRandomService questionRDSV;

    @Override
    public int calculateCorrectQuestion(int examtitleId) {
        int numberOfCorrectAnswer = 0;
        List<QuestionOfExamtitleEntity> listAnswerInListQuestionOfExamtitle = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId);
        for (QuestionOfExamtitleEntity q : listAnswerInListQuestionOfExamtitle) {
            if (q.getResultAnswerId() == q.getQuestion().getCorrectAnswerId()) {
                numberOfCorrectAnswer++;
            }
        }
        return numberOfCorrectAnswer;
    }

    @Override
    public List<ExamtitleEntity> getListExamtitleByStudentId(int studentId) {
        return examtitleRP.getListExamtitleByStudentId(studentId);
    }

    @Override
    public double markTheExam(int examtitleId) {
        int numberOfQuestionInThisExamtitle = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId).size();
        int numberOfCorrectAnswer = calculateCorrectQuestion(examtitleId);
        double point = Math.round((double) numberOfCorrectAnswer / numberOfQuestionInThisExamtitle * 100) / 10;
        return point;
    }

    @Override
    public ExamtitleEntity findExamtitleById(int i) {
        return examtitleRP.findByExamtitleId(i);
    }

    @Override
    public List<ExamtitleEntity> randomQuestionAndSave(int examId, int studentId) {

        Random random = new Random();
        int randomNumber = 0;
        List<QuestionRandomEntity> afterListQuestionRandom = new ArrayList<QuestionRandomEntity>();
        ExamtitleEntity examtitle = new ExamtitleEntity();
        List<QuestionRandomEntity> listQuestionRandom = questionRDSV.getAllByExamId(examId);
        List<QuestionEntity> listQuestion = new ArrayList<QuestionEntity>();
        List<QuestionOfExamtitleEntity> listQuestionOfExamTitle = new ArrayList<QuestionOfExamtitleEntity>();
        /*
        for (QuestionRandomEntity questionRandomEntity : listQuestionRandom) {
            System.out.println("TID:" + questionRandomEntity.getQuestionRandom());
        }System.out.println("size befor: " + listQuestionRandom.size());
         */
        while (!listQuestionRandom.isEmpty()) {

            randomNumber = random.nextInt((listQuestionRandom.size() - 1) + 1) + 1;
            //System.out.println("random:" + randomNumber + " | size: " + listQuestionRandom.size());
            afterListQuestionRandom.add(listQuestionRandom.get(randomNumber - 1));
            listQuestionRandom.remove(randomNumber - 1);
            //System.out.println("size after: " + listQuestionRandom.size());
        }

        //examtitle.set
        
        for (QuestionRandomEntity questionRandomEntity : afterListQuestionRandom) {
            System.out.println("SID:" + questionRandomEntity.getQuestionRandom() + "xxxx question:" + questionRandomEntity.getQuestionQR().getContent());
        }
         examtitle = examtitleRP.getListExamtitleByExamIdAndStudentId(examId, studentId);
        for (QuestionRandomEntity questionRandomEntity : afterListQuestionRandom) {
            QuestionOfExamtitleEntity questionOfExamTitleTemp = new QuestionOfExamtitleEntity();
            questionOfExamTitleTemp.setQuestion(questionRandomEntity.getQuestionQR());
            questionOfExamTitleTemp.setExamtitle(examtitle);
            listQuestionOfExamTitle.add(questionOfExamTitleTemp);
        }
        return null;
    }

}
