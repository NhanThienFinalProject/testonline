package com.testonline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "exam")
public class ExamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int examId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "PASSWORD")
    private String password;

    @JsonBackReference
    @Column(name = "TIMESTART")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @JsonBackReference
    @Column(name = "TIMEEND")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;

    @Column(name = "POINT")
    private int pointLadder;

    @Column(name = "MAXSTUDENT")
    private int maxStudent;

    @JsonBackReference
    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

//    reference to UserEntity by CREATEBYID
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    UserEntity user;

//    reference to ExamtitleEntity
    @JsonBackReference
    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY)
    List<ExamtitleEntity> listExamtitle;

//    reference to QuestionRandom
    @JsonBackReference
    @OneToMany(mappedBy = "examQR", fetch = FetchType.LAZY)
    List<QuestionRandomEntity> listQuestionRandom;

    public ExamEntity() {
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPointLadder() {
        return pointLadder;
    }

    public void setPointLadder(int pointLadder) {
        this.pointLadder = pointLadder;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ExamtitleEntity> getListExamtitle() {
        return listExamtitle;
    }

    public List<QuestionRandomEntity> getListQuestionRandom() {
        return listQuestionRandom;
    }

    public void setListQuestionRandom(List<QuestionRandomEntity> listQuestionRandom) {
        this.listQuestionRandom = listQuestionRandom;
    }

    public void setListExamtitle(List<ExamtitleEntity> listExamtitle) {
        this.listExamtitle = listExamtitle;
    }

    public int getTimeEndYear() {
        return timeEnd.getYear();
    }

    public int getTimeEndMonth() {
        return timeEnd.getMonthValue();
    }

    public int getTimeEndDay() {
        return timeEnd.getDayOfMonth();
    }

    public int getTimeEndHour() {
        return timeEnd.getHour();
    }

    public int getTimeEndMinute() {
        return timeEnd.getMinute();
    }

    public int getTimeEndSecond() {
        return timeEnd.getSecond();
    }

    public String getTimeEndString() {
        //String time = timeEnd.getMonth() + " " + timeEnd.getDayOfMonth() + ", " + timeEnd.getYear() + " " + timeEnd.getHour() + ":" + timeEnd.getMinute() + ":" + timeEnd.getSecond();

        return timeEnd.getMonth() + " " + timeEnd.getDayOfMonth() + ", " + timeEnd.getYear() + " " + timeEnd.getHour() + ":" + timeEnd.getMinute() + ":" + timeEnd.getSecond();
    }
    public String getTimeStartString() {
        return timeStart.getMonth() + " " + timeStart.getDayOfMonth() + ", " + timeStart.getYear() + " " + timeStart.getHour() + ":" + timeStart.getMinute() + ":" + timeStart.getSecond();
    }

}
