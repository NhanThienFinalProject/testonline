package com.testonline.service;

import com.testonline.entity.ExamEntity;
import java.util.List;


public interface IExamService {
    void saveExam(ExamEntity exam) ;
    List<ExamEntity> getAll();
    List<ExamEntity> getAllByUserId(int userId);
    ExamEntity getById(int id);
    ExamEntity getByIdAndUserId(int examId,int userId);
    ExamEntity getByStringMd5ExamId(String examId);
    public boolean checkPasswordOfExam(String password, int examId);
    public boolean checkIfCurrentStudentHaveSummittedYet(ExamEntity exam, int studentId);
    boolean isOnTime(int examId);
    String statusExam(int examId);
    boolean checkIfCurrentTeacherHadRequireExam(int teacherId, int examID);
    List<ExamEntity> getAllByStudentId(int studentId);
    List<ExamEntity> getFinishedExamOfCurrentTeacher(int teacherId);
    boolean checkPasswordOfStringExamId(String password, String examId);
    List<ExamEntity> getAllNotStartYetByTeacherId(int studentId);
    String getCurrentDateTime();
}
