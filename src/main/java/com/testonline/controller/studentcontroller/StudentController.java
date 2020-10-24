package com.testonline.controller.studentcontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.MesageService;
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
    @Autowired
    private MesageService mesage;

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
    public String showFormSubmitPassword(Model theModel, @RequestParam(value = "examId", required = true) String stringExamId, @RequestParam(value = "action", required = false) String action) {
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        if (action != null) {
            if (action.equals("errorpassword")) {
                mesage.putMesageWarning(theModel, "Password Room invalid! Please contact the teacher");
            } else if (action.equals("errorexamid")) {
                mesage.putMesageWarning(theModel, "ExamId Room invalid! Please contact the teacher");
            }
        }
        if (stringExamId == null) {
            mesage.putMesageWarning(theModel, "ExamId Room not empty! Please contact the teacher");
            return "student/form-submit-password";
            //return "redirect:student-submit-password?examId="+stringExamId;
        } else {

            ExamEntity exam = examSV.getExamEntityByMD5ExamId(stringExamId);
            if (exam == null) {
                mesage.putMesageWarning(theModel, "ExamId Room invalid! Please contact the teacher");
                return "student/form-submit-password";
                //return "redirect:student-submit-password?examId="+stringExamId;
            }
            theModel.addAttribute("exam", exam);
            theModel.addAttribute("stringExamId", userSV.md5(exam.getExamId() + ""));
            ExamtitleEntity examTitle = examtitleSV.findExamtitleByExamIdAndStudentId(exam.getExamId(), currentStudent.getUserId());
            if (examTitle == null) {
                return "student/form-submit-password";
                //return "redirect:waiting-exam-countdown?examId="+stringExamId;
            }

            theModel.addAttribute("student", currentStudent);
        }
        return "student/waitting-room";
    }

    @PostMapping(value = "student-submit-password-waitting-room")
    public String checkPasswordAndAddStudentToExam(Model theModel, @RequestParam("examId") String examIdRQ, @RequestParam("password") String password) {
        if (examIdRQ == null) {
            return "redirect:student-submit-password?examId=" + examIdRQ + "&action=errorexamid";
        }
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        ExamEntity exam = examSV.getExamEntityByMD5ExamId(examIdRQ);
        if (exam == null) {
            return "redirect:student-submit-password?examId=" + examIdRQ + "&action=errorexamid";
        }
        //  check password exam
        if (examSV.checkPasswordOfStringExamId(password, examIdRQ)) {
            ExamtitleEntity newExamtitleSaved = new ExamtitleEntity();
            ExamtitleEntity examtitle = examtitleSV.getExamtitleByExamIdAndStudentId(exam.getExamId(), currentStudent.getUserId());
            if (examtitle == null) {
                ExamtitleEntity newExamtitle = new ExamtitleEntity();
                newExamtitle.setExam(exam);
                newExamtitle.setStudent(currentStudent);
                newExamtitleSaved = examtitleSV.saveNewExamtitleForStudent(newExamtitle);
            }
            if (newExamtitleSaved != null) {
                mesage.putMesageSuccess(theModel, "Chúc mừng. Bạn đã được tham gia vào bài thi này");
            } else {
                mesage.putMesageDanger(theModel, "Tham Gia vào bài thi thất bại");
            }

        } else {
            //  give data back to password form submit to exam when invalid passwor           
            return "redirect:student-submit-password?examId=" + examIdRQ + "&action=errorpassword";
        }
        theModel.addAttribute("exam", exam);
        theModel.addAttribute("stringExamId", userSV.md5(exam.getExamId() + ""));
        theModel.addAttribute("student", currentStudent);
        return "student/waitting-room";
        
    }

    @GetMapping(value = "/waiting-exam")
    public String examIsWaiting(Model theModel) {
        UserEntity currentStudent = userSV.getDetailUserCurrent();
        List<ExamEntity> listExam = examSV.getAllByStudentId(currentStudent.getUserId());
        theModel.addAttribute("listExam", listExam);
        return "student/examwaiting";
    }

    @GetMapping(value = "/waiting-exam-countdown")
    public String examWaitingCountdown(Model theModel, @RequestParam(value = "examId", required = false) String stringExamId) {

        UserEntity currentStudent = userSV.getDetailUserCurrent();
        ExamEntity exam = examSV.getExamEntityByMD5ExamId(stringExamId);
        if (exam == null) {
            mesage.putMesageWarning(theModel, "ExamId invalid!");
            return "student/examwaiting";
        }
        theModel.addAttribute("exam", exam);
        theModel.addAttribute("student", currentStudent);
        return "student/waitting-room";
    }
}
