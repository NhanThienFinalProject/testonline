/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service;

import com.testonline.entity.ExamEntity;
import java.util.List;


public interface IExamService {
    void saveExam(ExamEntity exam) ;
    List<ExamEntity> getAll();
    List<ExamEntity> getAllByUserId(int userId);
    ExamEntity getById(int id);
    ExamEntity getByIdAndUserId(int examId,int userId);
    ExamEntity getByStringExamIdAndTeacherId(String examId, int teacherId);
    public boolean checkPasswordOfExam(String password, int examId, int teacherId);
    public boolean checkIfCurrentStudentHaveSummittedYet(ExamEntity exam, int studentId);
    boolean isOnTime(int examId);
    String statusExam(int examId);
}
