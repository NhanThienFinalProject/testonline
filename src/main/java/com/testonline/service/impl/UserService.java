/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.ExamtitleEntity;
import com.testonline.entity.UserEntity;
import com.testonline.repository.UserRepository;
import com.testonline.service.IExamService;
import com.testonline.service.IUserService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRP;
    
    @Autowired
    ExamtitleService examtitleSV;

    @Override
    public List<UserEntity> getAll() {
        return (List<UserEntity>) userRP.findAll();
    }

    @Override
    public void saveNewUser(UserEntity newUser) {
        userRP.save(newUser);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRP.findOneByUserName(username);
    }

    @Override
    public boolean isExistedUsername(String username) {
        UserEntity user = new UserEntity();
        user = userRP.findOneByUserName(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserEntity getDetailUserCurrent() {
        UserEntity user = new UserEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        user = userRP.findOneByUserName(userDetail.getUsername());
        return user;
    }

    @Override
    public UserEntity findUserByUserId(int userId) {
        return userRP.findByUserId(userId);
    }

    @Override
    public String md5(String string) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(string.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<UserEntity> findListStudentByTeacherId(int teacherId){
        List<ExamtitleEntity> listExamttleOfCurrentTeacher = examtitleSV.getExamtitleByTeacherId(teacherId);
        List<UserEntity> listStudentOfCurrentTeacher = new ArrayList<UserEntity>();
        for(ExamtitleEntity ex : listExamttleOfCurrentTeacher){
            UserEntity user = ex.getStudent();
            boolean check = false;
            for(UserEntity u : listStudentOfCurrentTeacher){
                if(u.getUserId() == user.getUserId()){
                    check = true;
                    break;
                }
            }
            if(!check){
                listStudentOfCurrentTeacher.add(user);
            }
        }
        return listStudentOfCurrentTeacher;
    }

}
