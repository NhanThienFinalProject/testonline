package com.testonline.controller.teachercontroller;

import com.testonline.entity.QuestionRandomEntity;
import com.testonline.service.impl.QuestionRandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionRandomController {

    @Autowired
    private QuestionRandomService QuestionRDSV;

    @PostMapping(value = "saveQuestionRandom")
    public String saveQuestionRandom(Model theModel, @RequestParam("examIdPR") String examIdPR, @RequestParam("questionIdPR") String[] questionIdPR) {
        String mesage = QuestionRDSV.checkAndSaveQuestionRandom(examIdPR, questionIdPR);
        return "redirect:teacher-detail-exam?examid=" + examIdPR;
    }

    //teacher-question-random-delete
    @GetMapping(value = "teacher-question-random-delete")
    public String deleteQuestionRandom(Model theModel, @RequestParam("examIdPR") String examIdPR, @RequestParam("questionRDId") String questionRandomId) {
        try {
            int id = Integer.parseInt(questionRandomId);
            QuestionRandomEntity questionRandom = QuestionRDSV.getById(id);
            QuestionRDSV.delete(questionRandom);
        } catch (Exception e) {
        }

        return "redirect:teacher-detail-exam?examid=" + examIdPR;
    }
}
