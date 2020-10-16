 
package com.testonline.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class UserEntity implements Serializable{
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    
//    reference to Role by ROLEID
    @ManyToOne
    @JoinColumn(name = "ROLEID")
    RolesEntity role;
    
//    reference to ExamEntity
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    List<ExamEntity> listExam;
    
//    reference to CategoryEntity
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<CategoryEntity> listCategory;
    
//    reference to ExamtitleEntity
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    List<ExamtitleEntity> listExamtitle;

    public UserEntity() {
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
    public String getFullName() {
        return firstName + " " + lastName;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    

     

    
   

    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }

    public List<ExamEntity> getListExam() {
        return listExam;
    }

    public void setListExam(List<ExamEntity> listExam) {
        this.listExam = listExam;
    }

    public List<CategoryEntity> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryEntity> listCategory) {
        this.listCategory = listCategory;
    }

    public List<ExamtitleEntity> getListExamtitle() {
        return listExamtitle;
    }

    public void setListExamtitle(List<ExamtitleEntity> listExamtitle) {
        this.listExamtitle = listExamtitle;
    }
    
    
}
