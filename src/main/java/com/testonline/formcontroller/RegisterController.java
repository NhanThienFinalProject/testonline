/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.formcontroller;

import com.testonline.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

 @Controller
public class RegisterController {
     @Autowired
    private UserService userSV;
    @GetMapping("/form-register")
    public String showFormRegister(Model theModel) {
	return "web/register";
    }
}
