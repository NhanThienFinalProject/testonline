
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
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    List<Answer> listAnswer;
    
//    reference to QuestionOfExamtitle
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    List<QuestionOfExamtitle> listQuestionOfExamtitle;

    public Question() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHinte() {
        return hinte;
    }

    public void setHinte(String hinte) {
        this.hinte = hinte;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public List<QuestionOfExamtitle> getListQuestionOfExamtitle() {
        return listQuestionOfExamtitle;
    }

    public void setListQuestionOfExamtitle(List<QuestionOfExamtitle> listQuestionOfExamtitle) {
        this.listQuestionOfExamtitle = listQuestionOfExamtitle;
    }
    
}
