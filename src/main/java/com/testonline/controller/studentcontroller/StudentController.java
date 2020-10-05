package com.testonline.controller.studentcontroller;

import com.testonline.entity.UserEntity;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private UserService userSV;

    @GetMapping("student-home")
    public String showStudentHome(Model theModel) {
        List<UserEntity> listUser = new ArrayList<UserEntity>();
        listUser = userSV.getAll();
        theModel.addAttribute("listUser", listUser);
        return "student/home";
    }
}
