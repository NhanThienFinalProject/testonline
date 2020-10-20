/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.api;


import com.testonline.service.impl.ExamtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-monitor-exam")
public class MonitorExamAPI {

    @Autowired
    ExamtitleService examtitleSV;

    @GetMapping(value = "/{examId}")
    public Object getListAttendeesOfExam(@PathVariable("examId") int examId) {
        return examtitleSV.getExamtitleByExamId(examId);
    }
}
