
package com.testonline.service;

import com.testonline.entity.ExamtitleEntity;
import java.util.List;


public interface IExamtitleService {
    public int calculateCorrectQuestion(int examtitleId);
    public List<ExamtitleEntity> getListExamtitleByStudentId(int studentId);
    public double markTheExam(int examtitleId);
    public ExamtitleEntity findExamtitleById(int examtitleId);
    public boolean checkExamtitleIfCurrentUserHas(int examtitleId, int studentId);
    public boolean checkIfExamIsFinished(int examtitleId);
    public ExamtitleEntity saveNewExamtitleForStudent(ExamtitleEntity examtitle);
    public ExamtitleEntity findExamtitleByExamIdAndStudentId(int examId, int studentId);
}
 