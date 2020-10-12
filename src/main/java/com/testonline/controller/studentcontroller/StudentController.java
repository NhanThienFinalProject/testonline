package com.testonline.controller.studentcontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
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
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private ExamService examSV;

    @GetMapping(value = "/student-home")
    public String showStudentHome(Model theModel) {
        List<UserEntity> listUser = new ArrayList<UserEntity>();
        listUser = userSV.getAll();
        theModel.addAttribute("listUser", listUser);
        return "student/result-details";
    }

    @GetMapping(value = {"/student-result", "/student-result/{page}"})
    public String checkResultAndMark(Model theModel, HttpSession session, HttpServletRequest req, @PathVariable Map<String, String> pathVariablesMap, @RequestParam("examtitleId") int examtitleId) {
//      check examtitleId if this student has
        boolean check = examtitleSV.checkExamtitleIfCurrentUserHas(examtitleId, userSV.getDetailUserCurrent().getUserId());
        if (check == true) {
//      paginate question result
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
            theModel.addAttribute("message", "");
        } else {
            theModel.addAttribute("message", "Somethings was wrong!");
        }
        return "student/result-details";
    }

    @GetMapping(value = "student-list-result")
    public String showListResult(Model theModel) {
        int currentUserId = userSV.getDetailUserCurrent().getUserId();
        List<ExamtitleEntity> listExamtitleOfCurrentStudent = examtitleSV.getListExamtitleByStudentId(currentUserId);
        for (ExamtitleEntity ex : listExamtitleOfCurrentStudent) {
            if (!examtitleSV.checkIfExamIsFinished(ex.getExamtitleId())) {
                listExamtitleOfCurrentStudent.remove(ex);
            }
        }
        theModel.addAttribute("listExamtitleOfCurrentStudent", listExamtitleOfCurrentStudent);
//        get  number of correct question of list of examtitle
        HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
        for (ExamtitleEntity ex : listExamtitleOfCurrentStudent) {
            double point = examtitleSV.markTheExam(ex.getExamtitleId());
            listResult.put(ex.getExamtitleId(), point);
        }
        theModel.addAttribute("listResult", listResult);
        return "student/list-results";
    }

    @GetMapping(value = "student-submit-password")
    public String showFormSubmitPassword(Model theModel, @RequestParam("examId") String stringExamId, @RequestParam("teacherId") int teacherId) {
        String view = "";
        int currentStudentId = userSV.getDetailUserCurrent().getUserId();
        theModel.addAttribute("studentId", currentStudentId);
//        get ExamID
        ExamEntity examNeedToJoin = examSV.getByStringExamIdAndTeacherId(stringExamId, teacherId);
        if (examNeedToJoin != null) {
//        check if current student have submitted password to the exam
            if (examSV.checkIfCurrentStudentHaveSummittedYet(examNeedToJoin, currentStudentId)) {
                theModel.addAttribute("studentId", currentStudentId);
                theModel.addAttribute("examId", examNeedToJoin.getExamId());
                theModel.addAttribute("teacherId", teacherId);
                theModel.addAttribute("examtitleId",examtitleSV.findExamtitleByExamIdAndStudentId(examNeedToJoin.getExamId(), currentStudentId).getExamtitleId());
                view = "student/waitting-room";
            } else {
                int examId = examNeedToJoin.getExamId();
                theModel.addAttribute("examId", examId);
                theModel.addAttribute("teacherId", teacherId);
                view = "student/form-submit-password";
            }
        } else {
            theModel.addAttribute("message", "Something was wrong! You need to check your exam's link correctly!");
            view = "student/result-details";
        }
        return view;
    }

    @PostMapping(value = "student-submit-password-waitting-room")
    public String checkPasswordAndAddStudentToExam(Model theModel, HttpServletRequest request) {
        String passwordExam = request.getParameter("password");
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int examId = Integer.parseInt(request.getParameter("examId"));
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
//        check password exam
        if (examSV.checkPasswordOfExam(passwordExam, examId, teacherId)) {
            ExamtitleEntity newExamtitle = new ExamtitleEntity();
            newExamtitle.setExam(examSV.getById(examId));
            newExamtitle.setStudent(userSV.findUserByUserId(studentId));
            ExamtitleEntity newExamtitleSaved = examtitleSV.saveNewExamtitleForStudent(newExamtitle);
            theModel.addAttribute("studentId", studentId);
            theModel.addAttribute("examId", examId);
            theModel.addAttribute("teacherId", teacherId);
            theModel.addAttribute("examtitleId", newExamtitleSaved.getExamtitleId());
        } else {
//            give data back to password form submit to exam when invalid password
            theModel.addAttribute("studentId", studentId);
            theModel.addAttribute("examId", examId);
            theModel.addAttribute("teacherId", teacherId);
            theModel.addAttribute("message", "not null");
            return "student/form-submit-password";
        }
        return "student/waitting-room";
    }
}
