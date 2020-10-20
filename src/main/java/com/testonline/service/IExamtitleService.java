
package com.testonline.service;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionRandomEntity;
import java.util.List;


public interface IExamtitleService {
    public int calculateCorrectQuestion(int examtitleId);
    public List<ExamtitleEntity> getListExamtitleByStudentId(int studentId);
    public double markTheExam(int examtitleId);
    public ExamtitleEntity findExamtitleById(int examtitleId);
    ExamtitleEntity randomQuestionAndSave(int examId,int studentId);
    ExamtitleEntity getExamtitleByExamIdAndStudentId(int examId,int studentId);
    public boolean checkExamtitleIfCurrentUserHas(int examtitleId, int studentId);
    public boolean checkIfExamIsFinished(int examtitleId);
    public ExamtitleEntity saveNewExamtitleForStudent(ExamtitleEntity examtitle);
    public ExamtitleEntity findExamtitleByExamIdAndStudentId(int examId, int studentId);
    public List<ExamtitleEntity> getExamtitleByTeacherIdAndExamId(int examId, int teacherId);
    public List<ExamtitleEntity> getExamtitleByExamId(int examId);
    public List<ExamtitleEntity> getExamtitleByTeacherId(int teacherId);
    public boolean checkExamtitleIfTeacherIdCreated(int examtitleId,int examId, int teacherId);
}
 
