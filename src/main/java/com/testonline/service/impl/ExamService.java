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
    
}
