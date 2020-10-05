
package com.testonline.teachercontroller;

import com.testonline.entity.AnswerEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.service.impl.AnswerService;
import com.testonline.service.impl.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowQuestionDetailsController {
    @Autowired
    QuestionService questionSV;
    @Autowired
    AnswerService answerSV;
    @GetMapping(value = "/teacher-question-details")
    public String showQuestionDetails(Model theModel,@RequestParam("questionId")int questionId ){
        QuestionEntity currentQuestion = questionSV.findQuestionByQuestionId(questionId);
        theModel.addAttribute("currentQuesion", currentQuestion);
        List<AnswerEntity> listAnswerOfCurrentQuestion = answerSV.findAnswersByQuestionId(questionId);
        theModel.addAttribute("listAnswers", listAnswerOfCurrentQuestion);
        return "teacher/show-question-details";
    }
}
