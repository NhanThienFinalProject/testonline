/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.QuestionOfExamtitleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOfExamTitleRepository extends CrudRepository<QuestionOfExamtitleEntity,Integer>{
    @Query("SELECT qoe FROM QuestionOfExamtitleEntity qoe WHERE qoe.examtitle.examtitleId = ?1")
    public List<QuestionOfExamtitleEntity> getListQuestionOfExamtitleByExamtitleId(int examtitleId);
}
