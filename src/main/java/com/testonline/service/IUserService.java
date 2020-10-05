/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service;

import com.testonline.entity.UserEntity;
import java.util.List;

public interface IUserService {

    List<UserEntity> getAll();
    UserEntity getByUserName(String userName);
    void saveNewUser(UserEntity newUser);
    
    UserEntity findUserByUsername(String username);
    
    boolean isExistedUsername(String username);

}
