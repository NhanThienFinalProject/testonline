
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
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int questionId;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "HINTE")
    private String hinte;
    
    @Column(name = "CORRECTANSWERID")
    private int correctAnswerId;
    
//    reference to Category by CATEGORYID
    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    Category category;
    
//    reference to Answer
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    List<Answer> listAnswer;
    
//    reference to QuestionOfExamtitle
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    List<QuestionOfExamtitle> listQuestionOfExamtitle;
}
