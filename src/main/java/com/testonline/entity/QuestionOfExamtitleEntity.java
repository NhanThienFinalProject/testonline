
package com.testonline.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questionofexamtitle")
public class QuestionOfExamtitleEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int questionOfExamtitleID;

    public int getQuestionOfExamtitleID() {
        return questionOfExamtitleID;
    }

    public void setQuestionOfExamtitleID(int questionOfExamtitleID) {
        this.questionOfExamtitleID = questionOfExamtitleID;
    }
    
    @Column(name = "RESULTANSWERID")
    private int resultAnswerId;
    
//    reference to ExamtitleEntity by ExamtitleId

    @ManyToOne
    @JoinColumn(name = "EXAMTITLEID")
    ExamtitleEntity examtitle;
    
//    reference to QuestionEntity by QuestionID
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTIONID")
    QuestionEntity question;

    public QuestionOfExamtitleEntity() {
    }

    public int getResultAnswerId() {
        return resultAnswerId;
    }

    public void setResultAnswerId(int resultAnswerId) {
        this.resultAnswerId = resultAnswerId;
    }

    public ExamtitleEntity getExamtitle() {
        return examtitle;
    }

    public void setExamtitle(ExamtitleEntity examtitle) {
        this.examtitle = examtitle;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
    
}
