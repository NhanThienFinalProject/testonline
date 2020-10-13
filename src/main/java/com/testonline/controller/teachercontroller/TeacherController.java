/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller.teachercontroller;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.UserService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
    @Autowired
    UserService userSV;
    
    @Autowired
    ExamtitleService examtitleSV;
    
    @GetMapping("/teacher-home")
    public String showTeacherHome(Model theModel) {
        return "teacher/home";
    }
    
    @GetMapping("/teacher-result-exam")
    public String showResultExam(Model theModel){
//        examID giả lập/ check if examId belongs to current teacher
        int examId = 1;
        int currentTeacherId = userSV.getDetailUserCurrent().getUserId();
        List<ExamtitleEntity> listExamtitleOfExamIDCurrentTeacher = examtitleSV.getExamtitleByTeacherIdAndExamId(examId,currentTeacherId);
        theModel.addAttribute("listExamtitleOfExamIDCurrentTeacher", listExamtitleOfExamIDCurrentTeacher);
        
//        get  number of correct question of list of examtitle
        HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
        for (ExamtitleEntity ex : listExamtitleOfExamIDCurrentTeacher) {
            double point = examtitleSV.markTheExam(ex.getExamtitleId());
            listResult.put(ex.getExamtitleId(), point);
        }
        theModel.addAttribute("listResult", listResult);
        return "teacher/result-detailsexam";
    }
}
