/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.entity.AnswerEntity;
import com.testonline.repository.AnswerRepository;
import com.testonline.service.IAnswerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService implements IAnswerService{
    @Autowired
    AnswerRepository answerRP;

    @Override
    public List<AnswerEntity> findAnswersByQuestionId(int questionId) {
        return answerRP.findAnswersByQuestionId(questionId);
    }

    @Override
    public AnswerEntity saveAnswer(AnswerEntity answer) {
        return answerRP.save(answer);
    }
    
}
