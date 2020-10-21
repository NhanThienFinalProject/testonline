package com.testonline.formcontroller;

import com.testonline.entity.UserEntity;
import com.testonline.service.impl.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public String showFormRegister(Model theModel) {;
        theModel.addAttribute("newUser", new UserEntity());
        return "web/register";
    }

    @PostMapping("/form-save")
    public String saveNewUser(Model theModel, @ModelAttribute("newUser") UserEntity newUser) {
        if (userSV.isExistedUsername(newUser.getUserName())) {
            return "web/register";
        } else {
            String password = newUser.getPassword();
            String passwordMD5 = userSV.md5(password);
            newUser.setPassword(passwordMD5);
            // convert datetime local
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);
            newUser.setCreateDate(now.parse(formatDateTime, formatter));
            userSV.saveNewUser(newUser);
            return "web/login";
        }
    }
}
