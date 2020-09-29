 
package com.testonline.entity;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int userId;
    
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "USERNAME")
    private String userName;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createDate;
    
//    reference to Role by ROLEID
    @ManyToOne
    @JoinColumn(name = "ROLEID")
    Roles role;
    
//    reference to Exam
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    List<Exam> listExam;
    
//    reference to Category
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Category> listCategory;
    
//    reference to Examtitle
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    List<Examtitle> listExamtitle;
    
    
}
