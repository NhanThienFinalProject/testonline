/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionRandomService;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userSV;
    @Autowired
    private ExamtitleService  examttSV;
    @Autowired 
    QuestionRandomService questionRDSV;
    @GetMapping("/showhome")
    public String showFormForAdd(Model theModel) {
	List<UserEntity> listUser = new ArrayList<UserEntity>();
        listUser = userSV.getAll();
        
        //List<ExamtitleEntity> listQTRD = examttSV.randomQuestionAndSave(12, 10);
	theModel.addAttribute("listUser", listUser);
	return "web/showhome";
    }
    
    @GetMapping("/teacher-homepage")
    public String showTeacherHome(Model theModel) {
	return "teacher/home";
    }
    @GetMapping(value = "/infomation-user")
    public String showStudentHome(Model theModel) {
        UserEntity user = userSV.getDetailUserCurrent();
        theModel.addAttribute("user", user);
        return "web/infomation";
    }

}
