
package com.testonline.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questionofexamtitle")
public class QuestionOfExamtitle implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int questionOfExamtitleID;
    @Column(name = "RESULTANSWERID")
    private int resultAnswerId;
    
//    reference to Examtitle by ExamtitleId

    @ManyToOne
    @JoinColumn(name = "EXAMTITLEID")
    Examtitle examtitle;
    
//    reference to Question by QuestionID
    @ManyToOne
    @JoinColumn(name = "QUESTIONID")
    Question question;

    public QuestionOfExamtitle() {
    }

    public int getResultAnswerId() {
        return resultAnswerId;
    }

    public void setResultAnswerId(int resultAnswerId) {
        this.resultAnswerId = resultAnswerId;
    }

    public Examtitle getExamtitle() {
        return examtitle;
    }

    public void setExamtitle(Examtitle examtitle) {
        this.examtitle = examtitle;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
}
