/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.api;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionOfExamtitleService;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-dotest")
public class DoTestAPI {

    @Autowired
    UserService userSV;
    @Autowired
    ExamService examSV;
    @Autowired
    ExamtitleService examTitleSV;
    @Autowired
    QuestionOfExamtitleService questionOfExamtitleSV;

    @GetMapping(value = "/{questionOfExamTitleId}")
    public Object listCustomers(@PathVariable("questionOfExamTitleId") int questionOfExamTitleId) {
        //UserEntity user = userSV.getDetailUserCurrent();        
        //ExamtitleEntity examTitle = examTitleSV.getExamtitleByExamIdAndStudentId(examId, user.getUserId());
        QuestionOfExamtitleEntity questionOfExamTitle = questionOfExamtitleSV.getById(questionOfExamTitleId);
        questionOfExamTitle.setExamtitle(questionOfExamTitle.getExamtitle());
        return questionOfExamTitle;
    }

    //@RequestParam("questionOfExamtitleID") int questionOfExamtitleID,@RequestParam("resultAnswerId") int resultAnswerId
    @RequestMapping(method = RequestMethod.POST)
    //@PostMapping()
    public Object saveAnswer(@RequestBody String answerData) {
        try {
            answerData = answerData.replace("\"","");
            String[] questionOfExamTitleIdAndResultId = answerData.split("#");
            System.out.println("x1: " + questionOfExamTitleIdAndResultId[0]);
            System.out.println("x3: " + questionOfExamTitleIdAndResultId[1]);
            QuestionOfExamtitleEntity questionOfExam = questionOfExamtitleSV.getById(Integer.parseInt(questionOfExamTitleIdAndResultId[0]));
            questionOfExam.setResultAnswerId(Integer.parseInt(questionOfExamTitleIdAndResultId[1]));
            questionOfExamtitleSV.saveQuestionOfExamTitle(questionOfExam);
            return true;
        } catch (Exception e) {
            return false;
        }

        //return new HashMap().put("message", "success");
        
    }
}
