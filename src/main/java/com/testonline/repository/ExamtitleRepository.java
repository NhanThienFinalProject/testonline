/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.ExamtitleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamtitleRepository extends CrudRepository<ExamtitleEntity, Integer>{
    @Query("SELECT ex FROM ExamtitleEntity ex WHERE ex.student.userId = ?1")
    public List<ExamtitleEntity> getListExamtitleByStudentId(int studentId);
    public ExamtitleEntity findByExamtitleId(int examtitleId);
    @Query("SELECT ex FROM ExamtitleEntity ex WHERE ex.examtitleId = ?1 AND ex.student.userId = ?2")
    public ExamtitleEntity findExamtitleByExamtitleIdAndStudentId(int examtitleId, int studentId);
    
    
}
