 
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
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int userId;
    
    @Column(name = "FIRTNAME")
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

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Exam> getListExam() {
        return listExam;
    }

    public void setListExam(List<Exam> listExam) {
        this.listExam = listExam;
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }

    public List<Examtitle> getListExamtitle() {
        return listExamtitle;
    }

    public void setListExamtitle(List<Examtitle> listExamtitle) {
        this.listExamtitle = listExamtitle;
    }
    
    
}
