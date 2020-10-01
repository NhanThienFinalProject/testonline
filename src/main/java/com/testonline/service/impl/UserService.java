/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.User;
import com.testonline.repository.UserRepository;
import com.testonline.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAll() {
        return (List<User>) userRP.findAll();
    }

    @Override
    public void saveNewUser(User newUser) {
        userRP.save(newUser);
    }

}
