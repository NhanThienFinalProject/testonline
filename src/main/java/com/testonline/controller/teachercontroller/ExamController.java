package com.testonline.controller.teachercontroller;

import com.testonline.entity.CategoryEntity;
import com.testonline.entity.ExamEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.CategoryService;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.QuestionRandomService;
import com.testonline.service.impl.UserService;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
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
    private QuestionRandomService questionRandomSV;
    @Autowired
    private CategoryService categorySV;

    @GetMapping(value = "/teacher-create-exam")
    public String showFormCreateExam(Model theModel) {
        theModel.addAttribute("examModel", new ExamEntity());
        PagedListHolder pagedListHolder = new PagedListHolder(examService.getAll());
        ArrayList<ExamEntity> listExam = (ArrayList<ExamEntity>) examService.getAllNotStartYetByTeacherId(userService.getDetailUserCurrent().getUserId());
        Collections.reverse(listExam);
        theModel.addAttribute("listExam",listExam);
        return "teacher/exam-form";

    }

    @PostMapping("teacher-save-exam")
    public String saveExam(Model theModel, @ModelAttribute("examModel") ExamEntity examEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findUserByUsername(authentication.getName());
        examEntity.setUser(user);
        examService.saveExam(examEntity);
        return "redirect:teacher-create-exam";
    }

    @GetMapping(value = "/teacher-detail-exam")
    public String detailExam(Model theModel, @RequestParam("examid") String idExam) {
        try {
            //detail exam
            int id = Integer.parseInt(idExam);
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
            List<CategoryEntity> listCategory = categorySV.findListCategoryAndListQuestionByUserIdAndExamId(userService.getDetailUserCurrent().getUserId(), id);
            if (!listCategory.isEmpty()) {
                theModel.addAttribute("listCategory", listCategory);
            }
            // Gen link of Examtitle
            theModel.addAttribute("linkExam", InetAddress. getLocalHost().getHostAddress()+":8080/NationalTestOnline/student-submit-password?examId=" + userService.md5(idExam));
        } catch (Exception e) {
            System.out.println("idExam invalid");
        }

        return "teacher/detailexam";
    }
}
