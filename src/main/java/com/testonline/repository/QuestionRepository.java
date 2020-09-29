/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.Question;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ADMIN
 */
public interface QuestionRepository extends CrudRepository<Question, Integer>{
    
}
