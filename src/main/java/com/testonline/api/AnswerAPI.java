/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.api;
 
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.MesageService;
import com.testonline.service.impl.QuestionOfExamtitleService;
import com.testonline.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-answer")
public class AnswerAPI {
   @Autowired
    UserService userSV;
    @Autowired
    ExamService examSV;
    @Autowired
    ExamtitleService examTitleSV;
    @Autowired
    QuestionOfExamtitleService questionOfExamtitleSV;
    @GetMapping(value = "/{examTitleId}")
    public Object getResultStudent(@PathVariable("examTitleId") int examTitleId) {
        ExamtitleEntity examtitle = examTitleSV.findExamtitleById(examTitleId);
        return examtitle;
    }
}
