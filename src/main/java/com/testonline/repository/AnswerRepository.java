/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.AnswerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Integer>{
    @Query("SELECT a FROM AnswerEntity a WHERE a.question.questionId =?1")
    public List<AnswerEntity> findAnswersByQuestionId(int questionId);
}
