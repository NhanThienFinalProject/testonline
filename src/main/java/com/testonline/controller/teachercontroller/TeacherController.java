/*   
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller.teachercontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherController {

    @Autowired
    UserService userSV;

    @Autowired
    ExamtitleService examtitleSV;

    @Autowired
    ExamService examSV;

    @GetMapping("/teacher-home")
    public String showTeacherHome(Model theModel) {
        return "teacher/home";
    }

    @GetMapping("/teacher-result-exam")
    public String showResultExam(Model theModel, @RequestParam("examId") int examId) {
        int currentTeacherId = userSV.getDetailUserCurrent().getUserId();
//        check if examId which current teacherId had
        if (examSV.checkIfCurrentTeacherHadRequireExam(currentTeacherId, examId)) {
            List<ExamtitleEntity> listExamtitleOfExamIDCurrentTeacher = examtitleSV.getExamtitleByTeacherIdAndExamId(examId, currentTeacherId);
            theModel.addAttribute("listExamtitleOfExamIDCurrentTeacher", listExamtitleOfExamIDCurrentTeacher);
            theModel.addAttribute("currentExam", examSV.getByIdAndUserId(examId, currentTeacherId));
//        get  number of correct question of list of examtitle
            HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
            for (ExamtitleEntity ex : listExamtitleOfExamIDCurrentTeacher) {
                double point = examtitleSV.markTheExam(ex.getExamtitleId());
                listResult.put(ex.getExamtitleId(), point);
            }
            theModel.addAttribute("listResult", listResult);
        }else{
            theModel.addAttribute("message", "Somethings was wrong!");
        }
        return "teacher/result-detailsexam";
    }

    @GetMapping("teacher-list-result-exam")
    public String showListFinishedExam(Model theModel) {
        UserEntity currentTeacher = userSV.getDetailUserCurrent();
        List<ExamEntity> listExamOfCurrentTeacher = examSV.getAllByUserId(currentTeacher.getUserId());
        List<ExamEntity> listFinishedExamOfCurrentTeacher = new ArrayList<ExamEntity>();
        for (ExamEntity ex : listExamOfCurrentTeacher) {
            if (examSV.statusExam(ex.getExamId()).equals("hoanthanh")) {
                listFinishedExamOfCurrentTeacher.add(ex);
            }
        }
        theModel.addAttribute("listFinishedExamOfCurrentTeacher", listFinishedExamOfCurrentTeacher);
        return "teacher/list-finished-exam";
    }
}
