/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.QuestionRandomEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRandomRepository extends CrudRepository<QuestionRandomEntity, Integer>{
    @Query("select qrd from QuestionRandomEntity qrd where qrd.examQR.examId = ?1 and qrd.examQR.user.userId = ?2 ")
    List<QuestionRandomEntity> findQuestionRandomByExamIdAndUserId(int examId,int userId);
}
