
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
public class ExamEntity implements Serializable{
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
    
    @Column(name = "TIMESTART")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStart;
    
    @Column(name = "TIMEEND")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeEnd;
    
    @Column(name = "POINT")
    private int pointLadder;
    
    @Column(name = "MAXSTUDENT")
    private int maxStudent;
    
    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createDate;
    
//    reference to UserEntity by CREATEBYID
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    UserEntity user;
    
//    reference to ExamtitleEntity
    @OneToMany(mappedBy = "exam")
    List<ExamtitleEntity> listExamtitle;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ExamtitleEntity> getListExamtitle() {
        return listExamtitle;
    }

    public void setListExamtitle(List<ExamtitleEntity> listExamtitle) {
        this.listExamtitle = listExamtitle;
    }
    
}
