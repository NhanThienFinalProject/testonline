/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller;

import com.testonline.entity.User;
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
    @GetMapping("/showhome")
    public String showFormForAdd(Model theModel) {
	List<User> listUser = new ArrayList<User>();
        listUser = userSV.getAll();
	theModel.addAttribute("listUser", listUser);
	return "index";
    }
    
    @GetMapping("/teacher-home")
    public String showTeacherHome(Model theModel) {
	return "homepage";
    }

}
