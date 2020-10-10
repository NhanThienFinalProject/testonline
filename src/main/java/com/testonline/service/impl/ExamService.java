/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.ExamEntity;
import com.testonline.repository.ExamRepository;
import com.testonline.service.IExamService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService implements IExamService{
    @Autowired
    private ExamRepository examRP;
    
    @Autowired
    private UserService userSV;
    
    @Override
    public void saveExam(ExamEntity exam) {
        examRP.save(exam);
    }

    @Override
    public List<ExamEntity> getAll() {
        return (List<ExamEntity>) examRP.findAll();
    }

    @Override
    public List<ExamEntity> getAllByUserId(int userId) {
        return examRP.findExamByUserId(userId);
    }

    @Override
    public ExamEntity getById(int id) {
        Optional<ExamEntity> examOp = examRP.findById(id);
        return examOp.isPresent()?examOp.get():null;
    }

    @Override
    public ExamEntity getByIdAndUserId(int examId, int userId) {
        return examRP.findExamByExamIdAndUserId(examId, userId);
    }

    @Override
    public ExamEntity getByStringExamIdAndTeacherId(String examId,int teacherId) {
//        get list exam that teacherId create
        List<ExamEntity> listExamOfTeacherId = examRP.findExamByUserId(teacherId);
        if(listExamOfTeacherId == null){
            return null;
        }
        for(ExamEntity ex : listExamOfTeacherId){
            if(userSV.md5(ex.getExamId()+"thien-nhan").equals(examId)){
                return ex;
            }
        }
        return null;
    }

    @Override
    public boolean checkPasswordOfExam(String password, int examId, int teacherId) {
        ExamEntity exam = examRP.findExamByExamIdAndUserId(examId, teacherId);
        if(exam == null){
            return false;
        }else{
            return exam.getPassword().equals(password);
        }
    }
    
}
