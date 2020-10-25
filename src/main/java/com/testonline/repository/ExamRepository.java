/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.ExamEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends CrudRepository<ExamEntity,Integer>{
    @Query("SELECT e FROM ExamEntity e WHERE e.user.userId = ?1")
    public List<ExamEntity> findExamByUserId(int userId);
    @Query(value ="select * from exam where md5(id)= ?1 ",nativeQuery = true)
    public ExamEntity findExamByUserIdAndMd5ExamId(String md5ExamId);
    @Query("SELECT e FROM ExamEntity e WHERE  e.examId = ?1 AND e.user.userId = ?2")
    ExamEntity findExamByExamIdAndUserId(int examId,int userId);
    
}
