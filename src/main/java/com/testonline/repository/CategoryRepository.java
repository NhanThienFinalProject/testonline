/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.Category;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ADMIN
 */
public interface CategoryRepository extends CrudRepository<Category, Integer>{
    
}
