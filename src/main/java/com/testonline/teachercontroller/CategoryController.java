
package com.testonline.teachercontroller;

import com.testonline.entity.CategoryEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.CategoryService;
import com.testonline.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CategoryController {
    @Autowired
    UserService userSV;
    @Autowired
    CategoryService categorySV;
    @GetMapping(value = "teacher-save-category")
    public String createNewCategory(Model theModel, @ModelAttribute("newCategory")CategoryEntity newCategory){
        int currentUserId = userSV.getCurrentUserid();
        UserEntity userCreatedCategory = userSV.findUserByUserId(currentUserId);
        newCategory.setUser(userCreatedCategory);
        categorySV.saveNewCategory(newCategory);
        return "redirect:teacher-create-question";
    }
}
