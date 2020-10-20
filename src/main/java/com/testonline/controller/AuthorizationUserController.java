/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller;

import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionRandomService;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizationUserController {
     @Autowired
    private UserService userSV;
    @Autowired
    private ExamtitleService  examttSV;
    @Autowired 
    QuestionRandomService questionRDSV;
    @RequestMapping(method = RequestMethod.GET)
    public void setSesionUser(HttpServletRequest request){
         request.getSession().setAttribute("currentUser", userSV.getDetailUserCurrent());
    }
    @RequestMapping(method = RequestMethod.GET)
    public void deleteSesionUser(HttpServletRequest request){
         request.getSession().removeAttribute("currentUser");
    }
    @GetMapping("/setauthorizationhome")
    public String setCurentUser(Model theModel,HttpServletRequest request) {
	UserEntity user = userSV.getDetailUserCurrent(); 
	setSesionUser(request);
	return "redirect:showhome";
    }
    @GetMapping("/deleteauthorizationhome")
    public String deleteCurentUser(Model theModel,HttpServletRequest request) {
	deleteSesionUser(request);
	return "redirect:form-login?logout";
    }
}
