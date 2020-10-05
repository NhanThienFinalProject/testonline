
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
public class QuestionEntity implements Serializable{
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
    
//    reference to CategoryEntity by CATEGORYID
    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    CategoryEntity category;
    
//    reference to AnswerEntity
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    List<AnswerEntity> listAnswer;
    
//    reference to QuestionOfExamtitleEntity
    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    List<QuestionOfExamtitleEntity> listQuestionOfExamtitle;
//      reference to QuestionRandom
    @OneToMany(mappedBy = "questionQR",fetch = FetchType.LAZY)
    List<QuestionRandomEntity> listQuestionRandom;
    public QuestionEntity() {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<AnswerEntity> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<AnswerEntity> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public List<QuestionOfExamtitleEntity> getListQuestionOfExamtitle() {
        return listQuestionOfExamtitle;
    }

    public void setListQuestionOfExamtitle(List<QuestionOfExamtitleEntity> listQuestionOfExamtitle) {
        this.listQuestionOfExamtitle = listQuestionOfExamtitle;
    }

    public List<QuestionRandomEntity> getListQuestionRandom() {
        return listQuestionRandom;
    }

    public void setListQuestionRandom(List<QuestionRandomEntity> listQuestionRandom) {
        this.listQuestionRandom = listQuestionRandom;
    }
    
}
