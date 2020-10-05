/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.UserEntity;
import com.testonline.repository.UserRepository;
import com.testonline.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    public int getCurrentUserid() {
        int userId = 0;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            userId = userRP.findOneByUserName(userDetail.getUsername()).getUserId();
        }
        return userId;
    }

    @Override
    public UserEntity findUserByUserId(int userId) {
        return userRP.findByUserId(userId);
    }

}
