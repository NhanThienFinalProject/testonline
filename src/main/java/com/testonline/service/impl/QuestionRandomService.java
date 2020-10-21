package com.testonline.service.impl;

import com.testonline.entity.ExamEntity;
import com.testonline.entity.QuestionEntity;
import com.testonline.entity.QuestionRandomEntity;
import com.testonline.repository.ExamRepository;
import com.testonline.repository.QuestionRandomRepository;
import com.testonline.repository.QuestionRepository;
import com.testonline.service.IQuestionRandomService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionRandomService implements IQuestionRandomService {

    @Autowired
    private QuestionRandomRepository questionRandomRP;
    @Autowired
    private ExamRepository examRP;
    @Autowired
    private QuestionRepository questionRP;

    @Override
    public List<QuestionRandomEntity> getAllByExamIdAndUserId(int examId, int userId) {
       /* List<QuestionRandomEntity> listQuestionRandom = questionRandomRP.findQuestionRandomByExamIdAndUserId(examId, userId);
         List<QuestionRandomEntity> listQuestionRandomUsing = new ArrayList<QuestionRandomEntity>();
        for (QuestionRandomEntity questionRandomEntity : listQuestionRandom) {
            QuestionEntity questionRD = new QuestionEntity();
            questionRandomEntity.setQuestionQR(questionRP.findByQuestionId(questionRD.getQuestionId()));
        } */
        return (List<QuestionRandomEntity>) questionRandomRP.findQuestionRandomByExamIdAndUserId(examId, userId);
    }

    @Override
    public void saveQuestionRandom(QuestionRandomEntity questionRandom) {
        questionRandomRP.save(questionRandom);
    }

    @Override
    public String checkAndSaveQuestionRandom(String examId, String[] listQuestionId) {
        int examUId = -1;
        int[] listUQuestionId = new int[listQuestionId.length];

        List<QuestionEntity> listQuestionEntity = new ArrayList<QuestionEntity>();
        ExamEntity examEntity = new ExamEntity();
        try {
            examUId = Integer.parseInt(examId);
            // chưa check exam xem có phải current user tạo hay không
            Optional<ExamEntity> exam = examRP.findById(examUId);
            examEntity = exam.isPresent() ? exam.get() : null;
        } catch (Exception e) {
            // thong bao mesage exam id khong hop le
            return "Exam id không hợp lệ";
        }
        // question
        try {

            for (int i = 0; i < listUQuestionId.length; i++) {
                listUQuestionId[i] = Integer.parseInt(listQuestionId[i]);
                listQuestionEntity.add(questionRP.findByQuestionId(listUQuestionId[i]));
            }
        } catch (Exception e) {
            // thong bao mesage question id khong hop le
            return "Question id không hợp lệ";
        }
        for (QuestionEntity question : listQuestionEntity) {
            QuestionRandomEntity questionRDModel = new QuestionRandomEntity();
            questionRDModel.setExamQR(examEntity);
            questionRDModel.setQuestionQR(question);
            questionRandomRP.save(questionRDModel);
        }
        return "done";
    }

    @Override
    public QuestionRandomEntity getById(int questionRandomId) {
         Optional<QuestionRandomEntity> questionRDModel2 = questionRandomRP.findById(questionRandomId);
        return  questionRDModel2.isPresent()?questionRDModel2.get():null;
    }

    @Override
    public void delete(QuestionRandomEntity questionRandomEntity) {
        questionRandomRP.delete(questionRandomEntity);
    }

    @Override
    public List<QuestionRandomEntity> getAllByExamId(int examId) {
        return questionRandomRP.findQuestionRandomByExamId(examId);
    }

}

