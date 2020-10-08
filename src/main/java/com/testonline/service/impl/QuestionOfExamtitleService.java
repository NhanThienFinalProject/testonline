package com.testonline.service.impl;

import com.testonline.entity.QuestionOfExamtitleEntity;
import com.testonline.repository.QuestionOfExamTitleRepository;
import com.testonline.service.IQuestionOfExamtitleService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

@Service
public class QuestionOfExamtitleService implements IQuestionOfExamtitleService {

    @Autowired
    QuestionOfExamTitleRepository questionOfExamtitleRP;

    @Override
    public List<QuestionOfExamtitleEntity> getListQuestionOfExamtitleByExamtitleId(int examtitleId) {
        return questionOfExamtitleRP.getListQuestionOfExamtitleByExamtitleId(examtitleId);
    }

    @Override
    public PagedListHolder<QuestionOfExamtitleEntity> paginateQuestion(String page, List<QuestionOfExamtitleEntity> list, HttpSession session) {
        PagedListHolder<QuestionOfExamtitleEntity> questionOfExamtitleList = new PagedListHolder<QuestionOfExamtitleEntity>();
        if (page == null || page.equals("")) {
//            set source for questionOfExamtitleList
            questionOfExamtitleList.setSource(list);
            questionOfExamtitleList.setPageSize(5);
            session.setAttribute("questionOfExamtitleList", questionOfExamtitleList);
        } else if (page.equals("previous")) {
//            switch to previous page
            questionOfExamtitleList = (PagedListHolder<QuestionOfExamtitleEntity>) session.getAttribute("questionOfExamtitleList");
            questionOfExamtitleList.previousPage();
        } else if (page.equals("next")) {
//            switch to next page
            questionOfExamtitleList = (PagedListHolder<QuestionOfExamtitleEntity>) session.getAttribute("questionOfExamtitleList");
            questionOfExamtitleList.nextPage();
        } else {
            int numberPage = Integer.parseInt(page);
            questionOfExamtitleList = (PagedListHolder<QuestionOfExamtitleEntity>) session.getAttribute("questionOfExamtitleList");
            questionOfExamtitleList.setPage(numberPage - 1);
        }
        return questionOfExamtitleList;
    }

}
