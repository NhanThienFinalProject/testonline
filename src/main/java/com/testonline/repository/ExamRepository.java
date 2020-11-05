/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.repository;

import com.testonline.entity.ExamEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends CrudRepository<ExamEntity,Integer>{
    @Query("SELECT e FROM ExamEntity e WHERE e.user.userId = ?1")
    public List<ExamEntity> findExamByUserId(int userId);
    @Query(value ="select * from exam where md5(id)= ?1 ",nativeQuery = true)
    public ExamEntity findExamByMd5ExamId(String md5ExamId);
    @Query("SELECT e FROM ExamEntity e WHERE  e.examId = ?1 AND e.user.userId = ?2")
    ExamEntity findExamByExamIdAndUserId(int examId,int userId);
    @Query(value ="SELECT e.ID,e.CONTENT,e.STATUS,e.PASSWORD,e.TIMESTART,e.TIMEEND,e.POINT,e.MAXSTUDENT,e.CREATEBYID,e.CREATEDATE FROM exam as e inner join examtitle as ett on e.ID = ett.EXAMID where e.TIMESTART >= ?1 and ett.STUDENTID  = ?2 ",nativeQuery = true)
    public List<ExamEntity> findByAllExamEntityTimeStartGreaterThanEquaAndStudentId(LocalDateTime now,int studentId);
    @Query(value ="SELECT e.ID,e.CONTENT,e.STATUS,e.PASSWORD,e.TIMESTART,e.TIMEEND,e.POINT,e.MAXSTUDENT,e.CREATEBYID,e.CREATEDATE FROM exam as e inner join user as u on e.CREATEBYID = u.ID where e.TIMEEND < ?1 and u.ID  = ?2 ",nativeQuery = true)
    public List<ExamEntity> findFinishedExamOfCurrentTeacher(LocalDateTime now,int teacherId);
    @Query(value ="SELECT e.ID,e.CONTENT,e.STATUS,e.PASSWORD,e.TIMESTART,e.TIMEEND,e.POINT,e.MAXSTUDENT,e.CREATEBYID,e.CREATEDATE FROM exam as e inner join user as u on e.CREATEBYID = u.ID where e.TIMESTART > ?1 and u.ID  = ?2 ",nativeQuery = true)
    public List<ExamEntity> findNotStartYetExamOfCurrentTeacher(LocalDateTime now,int teacherId);
}
