
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
@Table(name = "category")
public class CategoryEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int categoryId;
    
    @Column(name = "Name")
    private String categoryName;
    
//    reference to UserEntity by CREATEBYID
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    UserEntity user;
    
//  reference to QuestionEntity
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<QuestionEntity> listQuestion;

    public CategoryEntity() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<QuestionEntity> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionEntity> listQuestion) {
        this.listQuestion = listQuestion;
    }

   
    
}
