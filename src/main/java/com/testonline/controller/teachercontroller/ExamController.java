/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller.teachercontroller;

import com.testonline.entity.CategoryEntity;
import com.testonline.entity.ExamEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.CategoryService;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.QuestionRandomService;
import com.testonline.service.impl.QuestionService;
import com.testonline.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionSV;
    @Autowired
    private QuestionRandomService questionRandomSV;
    @Autowired
    private CategoryService categorySV;
    @GetMapping(value = "/teacher-create-exam")
    public String showStudentHome(Model theModel) {
        theModel.addAttribute("examModel", new ExamEntity());
        PagedListHolder pagedListHolder = new PagedListHolder(examService.getAll());
        theModel.addAttribute("listExam", examService.getAllByUserId(userService.getDetailUserCurrent().getUserId()));
        return "teacher/exam-form";

    }

    @PostMapping("saveExam")
    public String saveExam(Model theModel, @ModelAttribute("examModel") ExamEntity examEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(authentication.getName());
        examEntity.setUser(user);
        examService.saveExam(examEntity);
        return "redirect:teacher-create-exam";
    }

    @GetMapping(value = "/teacher-detail-exam")
    public String detailExam(Model theModel, @RequestParam("examid") String idExam) {
        int id = -1;
        try {
            //detail exam
            id = Integer.parseInt(idExam);
            ExamEntity exam = examService.getByIdAndUserId(id, userService.getDetailUserCurrent().getUserId());
            if (exam != null) {
                theModel.addAttribute("exam", exam);
            }
            // list question random
            List<QuestionRandomEntity> listQuestionRD = questionRandomSV.getAllByExamIdAndUserId(id, userService.getDetailUserCurrent().getUserId());
            if (!listQuestionRD.isEmpty()) {
                theModel.addAttribute("listQuestionRD", listQuestionRD);
            }
            // list question of category
            List<CategoryEntity> listCategory = categorySV.findListCategoryAndListQuestionByUserIdAndExamId(userService.getDetailUserCurrent().getUserId(),id);
            if (!listCategory.isEmpty()) {
                theModel.addAttribute("listCategory",listCategory);
            }
            // Gen link of Examtitle
            theModel.addAttribute("link", "http://localhost:8080/NationalTestOnline/student-submit-password?teacherId="+userService.getDetailUserCurrent().getUserId()+"&examId="+userService.md5(id+"thien-nhan"));
        } catch (Exception e) {
            System.out.println("idExam invalid");
        }

        return "teacher/detailexam";
    }
}
