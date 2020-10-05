/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.QuestionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer>{
    @Query("SELECT q FROM QuestionEntity q WHERE q.category.user.userId = ?1")
    public List<QuestionEntity> findQuestionByUserId(int id);
    
    public QuestionEntity findByQuestionId(int questionId);
}
