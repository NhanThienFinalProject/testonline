
package com.testonline.controller.teachercontroller;

import com.testonline.entity.CategoryEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.CategoryService;
import com.testonline.service.impl.QuestionService;
import com.testonline.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionSV;
    @Autowired
    UserService userSV;
    @Autowired
    CategoryService categorySV;
    
    @GetMapping(value = "/teacher-create-question")
    public String createQuestion(Model theModel){
        UserEntity user = userSV.getDetailUserCurrent();
//        get list questions for current user
        List<QuestionEntity> listQuestionDB = questionSV.findQuestionByUserId(user.getUserId());
        theModel.addAttribute("listQuestionDB",listQuestionDB);
//        get list category for current user
        List<CategoryEntity> listCategoryDB = categorySV.findListCategoryByUserId(user.getUserId());
        theModel.addAttribute("listCategoryDB", listCategoryDB);
        theModel.addAttribute("newQuestion", new QuestionEntity());
        theModel.addAttribute("newCategory", new CategoryEntity());
        // cái attribute này để làm gì bác
        //theModel.addAttribute("currentId", userID);
        return "teacher/create-question";
    }
    
    @PostMapping(value = "teacher-save-question")
    public String saveQuestion(Model theModel,@ModelAttribute("newQuestion")QuestionEntity newQuestion,@RequestParam("anotherAnswer") String[] anotherAnswers, @RequestParam("correctAnswer")String correctAnswer){
        String[] anotherAnswerWithoutNull = questionSV.deleteNullElement(anotherAnswers);
        questionSV.saveFullQuestion(newQuestion, anotherAnswerWithoutNull, correctAnswer);
        return "redirect:teacher-create-question";
    }
    
}
