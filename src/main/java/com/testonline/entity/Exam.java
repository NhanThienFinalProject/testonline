
package com.testonline.entity;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="exam")
public class Exam implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int examId;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "TIMESTART")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date timeStart;
    
    @Column(name = "TIMEEND")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date timeEnd;
    
    @Column(name = "POINT")
    private int pointLadder;
    
    @Column(name = "MAXSTUDENT")
    private int maxStudent;
    
    @Column(name = "CREATEDATE")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createDate;
    
//    reference to User by CREATEBYID
    @ManyToOne
    @JoinColumn(name = "CREATEBYID")
    User user;
    
//    reference to Examtitle
    @OneToMany(mappedBy = "exam")
    List<Examtitle> listExamtitle;
}
