/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service;

import com.testonline.entity.ExamEntity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IExamService {
    void saveExam(ExamEntity exam) ;
    List<ExamEntity> getAll();
}
