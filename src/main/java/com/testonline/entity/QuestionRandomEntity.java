/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "questionrandom")
public class QuestionRandomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int questionRandom;

    @ManyToOne
    @JoinColumn(name = "EXAMID")
    ExamEntity examQR;

    @ManyToOne
    @JoinColumn(name = "QUESTIONID")
    QuestionEntity questionQR;

    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    public QuestionRandomEntity() {
    }

    public int getQuestionRandom() {
        return questionRandom;
    }

    public void setQuestionRandom(int questionRandom) {
        this.questionRandom = questionRandom;
    }

    public ExamEntity getExamQR() {
        return examQR;
    }

    public void setExamQR(ExamEntity examQR) {
        this.examQR = examQR;
    }

    public QuestionEntity getQuestionQR() {
        return questionQR;
    }

    public void setQuestionQR(QuestionEntity questionQR) {
        this.questionQR = questionQR;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
