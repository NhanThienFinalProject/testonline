package com.testonline.controller.studentcontroller;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionOfExamtitleService;
import com.testonline.service.impl.QuestionService;
import com.testonline.service.impl.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private UserService userSV;

    @Autowired
    private ExamtitleService examtitleSV;

    @Autowired
    private QuestionOfExamtitleService questionOfExamtitleSV;

    @Autowired
    private QuestionService questionSV;

    @GetMapping(value = "/student-home")
    public String showStudentHome(Model theModel) {
        List<UserEntity> listUser = new ArrayList<UserEntity>();
        listUser = userSV.getAll();
        theModel.addAttribute("listUser", listUser);
        return "student/result-details";
    }

    @GetMapping(value = {"/student-result", "/student-result/{page}"})
    public String checkResultAndMark(Model theModel, HttpSession session, HttpServletRequest req, @PathVariable Map<String, String> pathVariablesMap, @RequestParam("examtitleId") int examtitleId) {

        String page = pathVariablesMap.get("page");
        List<QuestionOfExamtitleEntity> list = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId);
        PagedListHolder<QuestionOfExamtitleEntity> pageListHolder = questionOfExamtitleSV.paginateQuestion(page, list, session);
        theModel.addAttribute("examtitleId", examtitleId);
//        calculate number of correct answer (data for title header)
        int point = examtitleSV.calculateCorrectQuestion(examtitleId);
        theModel.addAttribute("point", point);
        int numberOfquestionOfExamtitle = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId).size();
        theModel.addAttribute("numberOfquestionOfExamtitle", numberOfquestionOfExamtitle);
        ExamtitleEntity currentExamtitle = examtitleSV.findExamtitleById(examtitleId);
        theModel.addAttribute("currentExamtitle", currentExamtitle);
        return "student/result-details";
    }

    @GetMapping(value = "student-list-result")
    public String showListResult(Model theModel) {
        int currentUserId = userSV.getDetailUserCurrent().getUserId();
        List<ExamtitleEntity> listExamtitleOfCurrentStudent = examtitleSV.getListExamtitleByStudentId(currentUserId);
        theModel.addAttribute("listExamtitleOfCurrentStudent", listExamtitleOfCurrentStudent);
//        get  number of correct question of list of examtitle
        HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
        for (ExamtitleEntity ex : listExamtitleOfCurrentStudent) {
            double point = examtitleSV.markTheExam(ex.getExamtitleId());
            listResult.put(ex.getExamtitleId(),point );
        }
        theModel.addAttribute("listResult", listResult);
        return "student/list-results";
    }
}
