/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.formcontroller;

import com.testonline.entity.User;
import com.testonline.service.impl.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

 @Controller
public class RegisterController {
    @Autowired
    private UserService userSV;
     
    @GetMapping("/form-register")
    public String showFormRegister(Model theModel) {
        String roleId = "student";
        theModel.addAttribute("newUser", new User());
	return "web/register";
    }
    
    @PostMapping("/form-save")
    public String saveNewUser(Model theModel, @ModelAttribute("newUser") User newUser) {
        newUser.setCreateDate(new Date());
//        System.out.println(newUser.getFirstName());
//        System.out.println(newUser.getLastName());
//        System.out.println(newUser.getCity());
//        System.out.println(newUser.getPhoneNumber());
//        System.out.println(newUser.getPassword());
//        System.out.println(newUser.getUserName());
//        System.out.println(newUser.getRole().getRoleId());
//        System.out.println(newUser.getUserId());
//        System.out.println(newUser.getCreateDate());
//        System.out.println(newUser.getEmail());
        
        userSV.saveNewUser(newUser);
	return "web/login";
    }
}
