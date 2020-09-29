
package com.testonline.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int categoryId;
    
    @Column(name = "Name")
    private String categoryName;
    
//    reference to User by CREATEBYID
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    User user;
    
//    reference to Question
    @OneToMany(mappedBy = "category")
    List<Category> listCategory;
    
}
