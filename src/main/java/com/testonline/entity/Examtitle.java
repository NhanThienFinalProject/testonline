/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examtitle")
public class Examtitle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int examtitleId;
    
//    reference to User by UserId(studentID)
    @ManyToOne
    @JoinColumn(name = "STUDENTID")
    User student;
    
//    reference to Exam by EXAMID
    @ManyToOne
    @JoinColumn(name = "EXAMID")
    Exam exam;
    
//    reference to QuestionOfExamtitle
    @OneToMany(mappedBy = "examtitle",fetch = FetchType.LAZY)
    List<QuestionOfExamtitle> listQuestionOfExamtitle;
    
    
}
