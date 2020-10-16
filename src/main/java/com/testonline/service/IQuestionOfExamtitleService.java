
package com.testonline.service;

import com.testonline.entity.QuestionOfExamtitleEntity;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;


public interface IQuestionOfExamtitleService {
    public List<QuestionOfExamtitleEntity> getListQuestionOfExamtitleByExamtitleId(int examtitleId);
    
    public PagedListHolder<QuestionOfExamtitleEntity> paginateQuestion(String page, List<QuestionOfExamtitleEntity> list, HttpSession session);
    QuestionOfExamtitleEntity getById(int id);
    void saveQuestionOfExamTitle(QuestionOfExamtitleEntity questionOfExamTitle);
}
