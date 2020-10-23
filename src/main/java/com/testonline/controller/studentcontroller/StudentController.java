package com.testonline.controller.studentcontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionOfExamtitleService;
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
    private ExamService examSV;

    @GetMapping(value = "/student-home")
    public String showStudentHome(Model theModel) {
        List<UserEntity> listUser = userSV.getAll();
        theModel.addAttribute("listUser", listUser);
        return "student/result-details";
    }

    @GetMapping(value = {"/result-student", "/result-student/{page}"})
    public String checkResultAndMark(Model theModel, HttpSession session, HttpServletRequest req, @PathVariable Map<String, String> pathVariablesMap, @RequestParam("examtitleId") int examtitleId, @RequestParam("teacherId") int teacherId, @RequestParam("examId") int examId) {
        //  check examtitleId if this student has OR if examtitle belongs to required teacher
        boolean checkStudentHasThisExamtitle = examtitleSV.checkExamtitleIfCurrentUserHas(examtitleId, userSV.getDetailUserCurrent().getUserId());
        boolean checkTeacherCreateThisExamtitle = examtitleSV.checkExamtitleIfTeacherIdCreated(examtitleId, examId, teacherId);
        if (checkStudentHasThisExamtitle || checkTeacherCreateThisExamtitle) {
            //  paginate question result
            String page = pathVariablesMap.get("page");
            List<QuestionOfExamtitleEntity> list = questionOfExamtitleSV.getListQuestionOfExamtitleByExamtitleId(examtitleId);
            PagedListHolder<QuestionOfExamtitleEntity> pageListHolder = questionOfExamtitleSV.paginateQuestion(page, list, session);
            theModel.addAttribute("examtitleId", examtitleId);
            //  calculate number of correct answer (data for title header)
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
        List<ExamtitleEntity> newListExamtitleOfCurrentStudent = new ArrayList<ExamtitleEntity>();
        //  check if the exam has finished yet
        for (ExamtitleEntity ex : listExamtitleOfCurrentStudent) {
            int examId = ex.getExam().getExamId();
            if (examSV.statusExam(examId).equals("hoanthanh")) {
                newListExamtitleOfCurrentStudent.add(ex);
            }
        }
        theModel.addAttribute("listExamtitleOfCurrentStudent", newListExamtitleOfCurrentStudent);
        //  get  point of list of examtitle
        HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
        for (ExamtitleEntity ex : listExamtitleOfCurrentStudent) {
            double point = examtitleSV.markTheExam(ex.getExamtitleId());
            listResult.put(ex.getExamtitleId(), point);
        }
        theModel.addAttribute("listResult", listResult);
        return "student/list-results";
    }

    @GetMapping(value = "/student-submit-password")
    public String showFormSubmitPassword(Model theModel, @RequestParam("examId") String stringExamId, @RequestParam("teacherId") int teacherId, @RequestParam Map<String, String> pathVariablesMap) {
        String action = pathVariablesMap.get("action");
        if (action != null) {
            if (action.equals("errorpassword")) {
                theModel.addAttribute("message", "not null");
            }
        }
        String view;
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        int currentStudentId = currentStudent.getUserId();
        theModel.addAttribute("studentId", currentStudentId);
        //  get current exam
        ExamEntity examNeedToJoin = examSV.getByStringExamIdAndTeacherId(stringExamId, teacherId);
        if (examNeedToJoin != null) {
            //  check if current student have submitted password to the exam
            if (examSV.checkIfCurrentStudentHaveSummittedYet(examNeedToJoin, currentStudentId)) {
                theModel.addAttribute("student", currentStudent);
                theModel.addAttribute("exam", examNeedToJoin);
                theModel.addAttribute("teacherId", teacherId);
                theModel.addAttribute("examtitleId", examtitleSV.findExamtitleByExamIdAndStudentId(examNeedToJoin.getExamId(), currentStudentId).getExamtitleId());
                view = "student/waitting-room";
            } else {
                int examId = examNeedToJoin.getExamId();
                theModel.addAttribute("stringExamId", stringExamId);
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
        String stringExamId = request.getParameter("stringExamId");
        int examId = Integer.parseInt(request.getParameter("examId"));
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        ExamEntity exam = examSV.getById(examId);
        //  check password exam
        if (examSV.checkPasswordOfExam(passwordExam, examId)) {
            ExamtitleEntity examtitle = examtitleSV.getExamtitleByExamIdAndStudentId(examId, currentStudent.getUserId());
            if (examtitle == null) {
                ExamtitleEntity newExamtitle = new ExamtitleEntity();
                newExamtitle.setExam(exam);
                newExamtitle.setStudent(currentStudent);
                ExamtitleEntity newExamtitleSaved = examtitleSV.saveNewExamtitleForStudent(newExamtitle);
                theModel.addAttribute("student", userSV.getDetailUserCurrent());
                theModel.addAttribute("exam", exam);
            }
        } else {
            //  give data back to password form submit to exam when invalid password
            theModel.addAttribute("action", "errorpassword");
            return "redirect:student-submit-password?examId=" + stringExamId + "&teacherId=" + exam.getUser().getUserId();
        }
        theModel.addAttribute("student", userSV.getDetailUserCurrent());
        theModel.addAttribute("exam", exam);
        return "student/waitting-room";
    }
    @GetMapping(value = "/waiting-exam")
    public String examIsWaiting(Model theModel) {
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        List<ExamEntity> listExam = examSV.getAllByStudentId(currentStudent.getUserId());
        theModel.addAttribute("listExam", listExam);
        return "student/examwaiting";
    }
}
