package com.testonline.service;

import com.testonline.entity.UserEntity;
import java.util.List;

public interface IUserService {

    List<UserEntity> getAll();
   
    void saveNewUser(UserEntity newUser);
    
    UserEntity findUserByUsername(String username);
    
    UserEntity findUserByUserId(int userId);
    
    boolean isExistedUsername(String username);
    
    UserEntity getDetailUserCurrent();
    
    String md5(String string);
    
    List<UserEntity> findListStudentByTeacherId(int teacherId);

}
