
package com.testonline.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="exam")
public class Exam implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int examId;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "TIMESTART")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date timeStart;
    
    @Column(name = "TIMEEND")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date timeEnd;
    
    @Column(name = "POINT")
    private int pointLadder;
    
    @Column(name = "MAXSTUDENT")
    private int maxStudent;
    
    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createDate;
    
//    reference to User by CREATEBYID
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    User user;
    
//    reference to Examtitle
    @OneToMany(mappedBy = "exam")
    List<Examtitle> listExamtitle;

    public Exam() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Examtitle> getListExamtitle() {
        return listExamtitle;
    }

    public void setListExamtitle(List<Examtitle> listExamtitle) {
        this.listExamtitle = listExamtitle;
    }
    
}
