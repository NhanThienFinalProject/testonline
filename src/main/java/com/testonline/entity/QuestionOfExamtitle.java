
package com.testonline.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questionofexamtitle")
public class QuestionOfExamtitle implements Serializable{
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
}
