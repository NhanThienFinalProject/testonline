
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.repository.ExamRepository;
import com.testonline.repository.ExamtitleRepository;
import com.testonline.service.IExamService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService implements IExamService {

    @Autowired
    private ExamRepository examRP;

    @Autowired
    private UserService userSV;

    @Autowired
    private ExamtitleRepository examtitleRP;

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
        return examOp.isPresent() ? examOp.get() : null;
    }

    @Override
    public ExamEntity getByIdAndUserId(int examId, int userId) {
        return examRP.findExamByExamIdAndUserId(examId, userId);
    }

    @Override
    public ExamEntity getByStringExamIdAndTeacherId(String examId, int teacherId) {
//        get list exam that teacherId create
        List<ExamEntity> listExamOfTeacherId = examRP.findExamByUserId(teacherId);
        if (listExamOfTeacherId == null) {
            return null;
        }
        for (ExamEntity ex : listExamOfTeacherId) {
            if (userSV.md5(ex.getExamId() + "thien-nhan").equals(examId)) {
                return ex;
            }
        }
        return null;
    }

    @Override
    public boolean checkPasswordOfExam(String password, int examId, int teacherId) {
        ExamEntity exam = examRP.findExamByExamIdAndUserId(examId, teacherId);
        if (exam == null) {
            return false;
        } else {
            return exam.getPassword().equals(password);
        }
    }

    @Override
    public boolean checkIfCurrentStudentHaveSummittedYet(ExamEntity exam, int studentId) {
        ExamtitleEntity examtitle = examtitleRP.findExamtitleByExamIdAndStudentId(exam.getExamId(), studentId);
        if (examtitle == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isOnTime(int examId) {
        //Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        Optional<ExamEntity> examOp = examRP.findById(examId);
        ExamEntity exam = examOp.isPresent() ? examOp.get() : null;
        // if (now.isBefore(exam.getTimeEnd().format(formatter)) && now.isAfter(exam.getTimeStart().parse(formatter)) ) {

        return false;
    }
    
     @Override
    public String statusExam(int examId) {
        LocalDateTime now = LocalDateTime.now();
        Optional<ExamEntity> examOp = examRP.findById(examId);
        ExamEntity exam = examOp.isPresent() ? examOp.get() : null;
        if (now.isBefore(exam.getTimeStart())) {
            return "chuabatdau";
        }else if (now.isAfter(exam.getTimeEnd())) {
            return "hoanthanh";
        }else if (now.isBefore(exam.getTimeEnd()) && now.isAfter(exam.getTimeStart())) {
            return "dangthi";
        }
        return "";
    }

    @Override
    public boolean checkIfCurrentTeacherHadRequireExam(int teacherId, int examID) {
        ExamEntity requireExam = examRP.findExamByExamIdAndUserId(examID, teacherId);
        return requireExam != null;
    }
}
