package com.testonline.service.impl;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.repository.ExamtitleRepository;
import com.testonline.service.IExamtitleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamtitleService implements IExamtitleService {

    @Autowired
    private QuestionOfExamtitleService questionOfExamtitleSV;

    @Autowired
    private ExamtitleRepository examtitleRP;

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
    public boolean checkExamtitleIfCurrentUserHas(int examtitleId, int studentId) {
        if (examtitleRP.findExamtitleByExamtitleIdAndStudentId(examtitleId, studentId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIfExamIsFinished(int examtitleId) {
//        ExamtitleEntity examtitle = examtitleRP.findByExamtitleId(examtitleId);
//        LocalDate date = LocalDate.now();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        String dateString = df.format(date);
//        Date currenDate;
//        try {
//            currenDate = df.parse(dateString);
//            if (examtitle.getExam().getTimeEnd().before(currenDate)) {
//                return true;
//            }
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
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

}
