/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.controller.studentcontroller;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.MesageService;
import com.testonline.service.impl.QuestionOfExamtitleService;
import com.testonline.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoTestController {

    @Autowired
    MesageService mesageSV;
    @Autowired
    private UserService userSV;
    @Autowired
    ExamService examSV;
    @Autowired
    QuestionOfExamtitleService questionOfExamTitleSV;
    @Autowired
    ExamtitleService examTitleSV;

    @GetMapping("student-dotest/{examId}")
    public String getDoTest(@RequestParam("examId") String examId) {
        int userId = userSV.getDetailUserCurrent().getUserId();
        return "";
    }

    @GetMapping("/exam-dotest")
    public String doTest(Model theModel, @RequestParam("examId") String examId) {
        UserEntity user = userSV.getDetailUserCurrent();
        int examIdInt = -1;
        try {
            examIdInt = Integer.parseInt(examId);
        } catch (Exception e) {
            mesageSV.putMesageWarning(theModel, "Mã bài thi không hợp lệ");
            return "student/exam";
        }
        // kiểm tra student đã được add vào  kì thi này hay chưa
        ExamtitleEntity examtitle = examTitleSV.getExamtitleByExamIdAndStudentId(examIdInt, user.getUserId());
        if (examtitle == null) {
            mesageSV.putMesageWarning(theModel, "Bạn không có tên trong bài thi này vui lòng liên hệ với giảng viên để biết thêm thông tin.");
            return "student/exam";
        } else {
            //kiểm tra giờ thi
            String examStatus = examSV.statusExam(examIdInt);
            if (examStatus.equals("chuabatdau")) {
                mesageSV.putMesageWarning(theModel, "Chưa đến giờ thi vui lòng quay lại trang chờ thi.");
                return "student/exam";
            } else if (examStatus.equals("hoanthanh")) {
                mesageSV.putMesageWarning(theModel, "Bài thi này đã kết thúc.");
                return "student/exam";
            } else if (examStatus.equals("dangthi")) {
                mesageSV.putMesageSuccess(theModel, "Mẹo nhỏ: Hãy làm các câu dễ trước sau đó quay lại làm các câu khó sau");
                theModel.addAttribute("examStatus", "active");
                theModel.addAttribute("examDetail", examSV.getById(examIdInt));
                theModel.addAttribute("userDetail", user);
                int[] listQuestionId = new int[examtitle.getListQuestionOfExamtitle().size()];
                int i = 0;
                theModel.addAttribute("examTitle", examtitle);
                for (QuestionOfExamtitleEntity qOE : examtitle.getListQuestionOfExamtitle()) {
                    listQuestionId[i] = qOE.getQuestionOfExamtitleID();
                    i++;
                }
                theModel.addAttribute("listExamTitleId", listQuestionId);

            } else {
                mesageSV.putMesageWarning(theModel, "Lỗi giờ thi không xát định.");
                return "student/exam";
            }

        }
        return "student/exam";
    }
    @GetMapping("/exam-createexamtitle")
    public String createExamtitle(Model theModel, @RequestParam("examId") String examId) {
         UserEntity user = userSV.getDetailUserCurrent();
        int examIdInt = -1;
        try {
            examIdInt = Integer.parseInt(examId);
        } catch (Exception e) {
            mesageSV.putMesageWarning(theModel, "Mã bài thi không hợp lệ");
            return "student/exam";
        }
        // kiểm tra student đã được add vào  kì thi này hay chưa
        ExamtitleEntity examtitle = examTitleSV.getExamtitleByExamIdAndStudentId(examIdInt, user.getUserId());
        if (examtitle.getListQuestionOfExamtitle().isEmpty()) {
            ExamtitleEntity examtitleTemp = examTitleSV.randomQuestionAndSave(examIdInt, user.getUserId());                        
        }  
        return "redirect:exam-dotest?examId=" + examId;
    }
}
