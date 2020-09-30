 
package com.testonline.formcontroller;

import com.testonline.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

 @Controller
public class LoginController {
     @Autowired
    private UserService userSV;
    @GetMapping("/form-login")
    public String showFormLogin(Model theModel) {
	return "web/login";
    }
}
