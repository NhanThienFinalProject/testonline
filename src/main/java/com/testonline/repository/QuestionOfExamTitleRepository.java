/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.QuestionOfExamtitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOfExamTitleRepository extends CrudRepository<QuestionOfExamtitle,Integer>{
    
}