package com.testonline.service.impl;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.repository.ExamtitleRepository;
import com.testonline.repository.QuestionOfExamTitleRepository;
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
    QuestionOfExamTitleRepository questionOfExamtitleRP;
    @Autowired
    QuestionService questionSV;
    @Autowired
    private ExamtitleRepository examtitleRP;
    @Autowired
    QuestionRandomService questionRDSV;

    @Autowired
    private ExamService examSV;

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
        ExamEntity requiredExam = examtitleRP.findByExamtitleId(examtitleId).getExam();
        int numberOfQuestionInThisExamtitle = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId).size();
        int numberOfCorrectAnswer = calculateCorrectQuestion(examtitleId);
        double point = Math.round((double) numberOfCorrectAnswer / numberOfQuestionInThisExamtitle * requiredExam.getPointLadder());
        return point;
    }

    @Override
    public ExamtitleEntity findExamtitleById(int i) {
        return examtitleRP.findByExamtitleId(i);
    }

    @Override
    public ExamtitleEntity randomQuestionAndSave(int examId, int studentId) {

        Random random = new Random();
        int randomNumber = 0;
        List<QuestionRandomEntity> afterListQuestionRandom = new ArrayList<QuestionRandomEntity>();
        ExamtitleEntity examtitle = new ExamtitleEntity();
        List<QuestionEntity> listQuestion = new ArrayList<QuestionEntity>();
        List<QuestionOfExamtitleEntity> listQuestionOfExamTitle = new ArrayList<QuestionOfExamtitleEntity>();

        List<QuestionRandomEntity> listQuestionRandom = questionRDSV.getAllByExamId(examId);
        // random list questionrandom to [afterListQuestionRandom]
        while (!listQuestionRandom.isEmpty()) {

            randomNumber = random.nextInt((listQuestionRandom.size() - 1) + 1) + 1;
            //System.out.println("random:" + randomNumber + " | size: " + listQuestionRandom.size());
            afterListQuestionRandom.add(listQuestionRandom.get(randomNumber - 1));
            listQuestionRandom.remove(randomNumber - 1);
            //System.out.println("size after: " + listQuestionRandom.size());
        }

        // find examtitle of student
        examtitle = examtitleRP.findExamtitleByExamIdAndStudentId(examId, studentId);

        for (QuestionRandomEntity questionRandomEntity : afterListQuestionRandom) {
            QuestionOfExamtitleEntity questionOfExamTitleTemp = new QuestionOfExamtitleEntity();
            //set question of questionOfExamTitle
            questionOfExamTitleTemp.setQuestion(questionRandomEntity.getQuestionQR());
            //set exam of questionOfExamTitle
            questionOfExamTitleTemp.setExamtitle(examtitle);
            // add list to save
            listQuestionOfExamTitle.add(questionOfExamTitleTemp);
        }
        questionOfExamtitleRP.saveAll(listQuestionOfExamTitle);
        return examtitleRP.findExamtitleByExamIdAndStudentId(examId, studentId);
    }

    @Override
    public ExamtitleEntity getExamtitleByExamIdAndStudentId(int examId, int studentId) {
        return examtitleRP.findExamtitleByExamIdAndStudentId(examId, studentId);
    }

    @Override
    public boolean checkExamtitleIfCurrentUserHas(int examtitleId, int studentId) {
        if (examtitleRP.findExamtitleByExamtitleIdAndStudentId(examtitleId, studentId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public ExamtitleEntity saveNewExamtitleForStudent(ExamtitleEntity examtitle) {
        return examtitleRP.save(examtitle);
    }

    @Override
    public ExamtitleEntity findExamtitleByExamIdAndStudentId(int examId, int studentId) {
        return examtitleRP.findExamtitleByExamIdAndStudentId(examId, studentId);
    }

    @Override
    public List<ExamtitleEntity> getExamtitleByTeacherIdAndExamId(int examId, int teacherId) {
        return examtitleRP.findExamtitleByTeacherIdAndExamId(examId, teacherId);
    }

    @Override
    public boolean checkExamtitleIfTeacherIdCreated(int examtitleId, int examId, int teacherId) {
        if (examtitleRP.findExamtitleByTeacherIdAndExamIdAndExamtitleId(examtitleId, examId, teacherId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<ExamtitleEntity> getExamtitleByTeacherId(int teacherId) {
        return examtitleRP.findExamtitleByTeacher(teacherId);
    }

    @Override
    public List<ExamtitleEntity> getExamtitleByExamId(int examId) {
        return examtitleRP.findExamtitleByExamId(examId);
    }

}
