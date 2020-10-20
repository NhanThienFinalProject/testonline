/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
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

@Entity
@Table(name = "examtitle")
public class ExamtitleEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int examtitleId;
    
//    reference to UserEntity by UserId(studentID)
//    @JsonBackReference
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "STUDENTID")
    UserEntity student;
    
//    reference to ExamEntity by EXAMID
//    @JsonBackReference
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "EXAMID")
    ExamEntity exam;
    
//    reference to QuestionOfExamtitleEntity
    @JsonManagedReference
//    @JsonBackReference
    @OneToMany(mappedBy = "examtitle",fetch = FetchType.LAZY)
    List<QuestionOfExamtitleEntity> listQuestionOfExamtitle;

    public ExamtitleEntity() {
    }

    public int getExamtitleId() {
        return examtitleId;
    }

    public void setExamtitleId(int examtitleId) {
        this.examtitleId = examtitleId;
    }

    public UserEntity getStudent() {
        return student;
    }

    public void setStudent(UserEntity student) {
        this.student = student;
    }

    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }

    public List<QuestionOfExamtitleEntity> getListQuestionOfExamtitle() {
        return listQuestionOfExamtitle;
    }

    public void setListQuestionOfExamtitle(List<QuestionOfExamtitleEntity> listQuestionOfExamtitle) {
        this.listQuestionOfExamtitle = listQuestionOfExamtitle;
    }
    
    
}
