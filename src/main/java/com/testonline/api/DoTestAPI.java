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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public QuestionOfExamtitleEntity listCustomers(@PathVariable("questionOfExamTitleId") int questionOfExamTitleId) {
        //UserEntity user = userSV.getDetailUserCurrent();
        
        //ExamtitleEntity examTitle = examTitleSV.getExamtitleByExamIdAndStudentId(examId, user.getUserId());
        QuestionOfExamtitleEntity questionOfExamTitle = questionOfExamtitleSV.getById(questionOfExamTitleId);
        questionOfExamTitle.setExamtitle(questionOfExamTitle.getExamtitle());

        if (questionOfExamTitle != null) {
            return questionOfExamTitle;
        }
        return null;
    }
    @PostMapping()
    public Object saveAnswer(@PathVariable("questionOfExamTitleId") int questionOfExamTitleId,@PathVariable("answerId") int answerId) {
	 QuestionOfExamtitleEntity questionOfExam = questionOfExamtitleSV.getById(questionOfExamTitleId);
         questionOfExam.setResultAnswerId(answerId);
         questionOfExamtitleSV.saveQuestionOfExamTitle(questionOfExam);
	return new HashMap().put("message", "sucess");
    }
}
