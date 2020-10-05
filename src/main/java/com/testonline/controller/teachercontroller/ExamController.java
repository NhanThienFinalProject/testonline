/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller.teachercontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/teacher-create-exam")
    public String showStudentHome(Model theModel) {
        theModel.addAttribute("examModel", new ExamEntity());
        PagedListHolder pagedListHolder = new PagedListHolder(examService.getAll());
        theModel.addAttribute("listExam", examService.getAll());
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
}
