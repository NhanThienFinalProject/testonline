package com.testonline.controller.teachercontroller;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.UserEntity;
import com.testonline.service.impl.ExamService;
import com.testonline.service.impl.ExamtitleService;
import com.testonline.service.impl.QuestionService;
import com.testonline.service.impl.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    @Autowired
    UserService userSV;
    
    @Autowired
    QuestionService questionSV;

    @Autowired
    ExamtitleService examtitleSV;

    @Autowired
    ExamService examSV;

    @GetMapping("/teacher-home")
    public String showTeacherHome(Model theModel) {
        UserEntity user = userSV.getDetailUserCurrent();
        //  get 4 the most recent exam of current student
        List<ExamEntity> listFinishedExam = examSV.getFinishedExamOfCurrentTeacher(user.getUserId());
        theModel.addAttribute("listFinishedExam", listFinishedExam);
        //  get list questions for current user
        List<QuestionEntity> listQuestionDB = questionSV.findQuestionByUserId(user.getUserId());
        theModel.addAttribute("listQuestionDB", listQuestionDB);
        return "teacher/index";
    }

    @GetMapping("/teacher-result-exam")
    public String showResultExam(Model theModel, @RequestParam("examId") int examId) {
        int currentTeacherId = userSV.getDetailUserCurrent().getUserId();
        //  check if examId which current teacherId had
        if (examSV.checkIfCurrentTeacherHadRequireExam(currentTeacherId, examId)) {
            List<ExamtitleEntity> listExamtitleOfExamIDCurrentTeacher = examtitleSV.getExamtitleByTeacherIdAndExamId(examId, currentTeacherId);
            theModel.addAttribute("listExamtitleOfExamIDCurrentTeacher", listExamtitleOfExamIDCurrentTeacher);
            theModel.addAttribute("currentExam", examSV.getByIdAndUserId(examId, currentTeacherId));
            //  get  number of correct question of list of examtitle
            HashMap<Integer, Double> listResult = new HashMap<Integer, Double>();
            for (ExamtitleEntity ex : listExamtitleOfExamIDCurrentTeacher) {
                double point = examtitleSV.markTheExam(ex.getExamtitleId());
                listResult.put(ex.getExamtitleId(), point);
            }
            theModel.addAttribute("listResult", listResult);
        } else {
            theModel.addAttribute("message", "Somethings was wrong!");
        }
        return "teacher/result-detailsexam";
    }

    @GetMapping("teacher-manage-exam")
    public String showListFinishedExam(Model theModel) {
        UserEntity currentTeacher = userSV.getDetailUserCurrent();
        List<ExamEntity> listExamOfCurrentTeacher = examSV.getAllByUserId(currentTeacher.getUserId());
        //   get list completed exam vs list happing exam
        List<ExamEntity> listFinishedExamOfCurrentTeacher = new ArrayList<ExamEntity>();
        List<ExamEntity> listHappeningExamOfCurrentTeacher = new ArrayList<ExamEntity>();
        for (ExamEntity ex : listExamOfCurrentTeacher) {
            if (examSV.statusExam(ex.getExamId()).equals("hoanthanh")) {
                listFinishedExamOfCurrentTeacher.add(ex);
            }
            if (examSV.statusExam(ex.getExamId()).equals("dangthi")) {
                listHappeningExamOfCurrentTeacher.add(ex);
            }
        }
        theModel.addAttribute("listFinishedExamOfCurrentTeacher", listFinishedExamOfCurrentTeacher);
        theModel.addAttribute("listHappeningExamOfCurrentTeacher", listHappeningExamOfCurrentTeacher);
        return "teacher/list-exam";
    }

    @GetMapping("teacher-preparing-sendmail")
    public String showListEmailStudent(Model theModel, @RequestParam("examId") int examId, @RequestParam("link-exam") String link) {
        UserEntity currentTeacher = userSV.getDetailUserCurrent();
        ExamEntity requiredExam = examSV.getByIdAndUserId(examId, currentTeacher.getUserId());
        if (requiredExam != null) {
            theModel.addAttribute("requiredExam", requiredExam);
            theModel.addAttribute("linkExam", link);
            //  get list current teacher's student
            List<UserEntity> listStudentOfCurrentStudent = userSV.findListStudentByTeacherId(currentTeacher.getUserId());
            theModel.addAttribute("listStudentOfCurrentStudent", listStudentOfCurrentStudent);
        } else {
            theModel.addAttribute("message", "Somethings was wrong!");
        }
        return "teacher/form-input-email";
    }

    @PostMapping("teacher-sendmail")
    public String sendMailBySMTP(Model theModel, HttpServletRequest req) {
        String[] listEmail = req.getParameterValues("exampleCheck1");
        String linkExam = req.getParameter("link");
        int examId = Integer.parseInt(req.getParameter("examId"));
        ExamEntity requiredExam = examSV.getById(examId);

        // Sender's email ID needs to be mentioned.
        String from = "thiennguyen15dt1@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("thiennguyen15dt1@gmail.com", "khanhdung0908");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        for (String email : listEmail) {
            // Recipient's email ID needs to be mentioned
            String to = email;
            System.out.println(to);
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("National Test Online");

                // Set time xxx
                String timeStart = formatTime(requiredExam.getTimeStart());
                String timeEnd = formatTime(requiredExam.getTimeEnd());

                // Now set the actual message
                String mess = "Exam: " + requiredExam.getContent() + "\n"
                        + "Time: " + timeStart + " - " + timeEnd + " on " + requiredExam.getTimeStart().getDayOfMonth() + " " + requiredExam.getTimeStart().getMonth() + " " + requiredExam.getTimeStart().getYear() + "\n"
                        + "Link: " + linkExam + "\n"
                        + "Password: " + requiredExam.getPassword();
                message.setText(mess);

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
        return "teacher/home";
    }

    @GetMapping("teacher-monitoring-exam")
    public String showMonitoringRoom(Model theModel, @RequestParam("examId") int examId) {
        //  check if current exam create required exam
        if (examSV.getById(examId) != null && examSV.getById(examId).getUser().getUserId() == userSV.getDetailUserCurrent().getUserId()) {
            if (examSV.statusExam(examId).equals("dangthi")) {
                theModel.addAttribute("requiredExam", examSV.getById(examId));
            } else if (examSV.statusExam(examId).equals("hoanthanh")) {
                theModel.addAttribute("message", "Exam was seem finished!");
            } else {
                theModel.addAttribute("message", "Exam is not started!");
            }
        } else {
            theModel.addAttribute("message", "Something was wrong!");
        }
        return "teacher/room-monitoring-exam";
    }

    public String formatTime(LocalDateTime time) {
        String h = String.valueOf((time.getHour() < 10) ? "0" + time.getHour() : time.getHour());
        String m = String.valueOf((time.getMinute() < 10) ? "0" + time.getMinute() : time.getMinute());
        return h + ":" + m;
    }
}
