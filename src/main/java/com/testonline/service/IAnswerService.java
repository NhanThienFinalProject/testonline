
package com.testonline.service;

import com.testonline.entity.AnswerEntity;
import java.util.List;


public interface IAnswerService {
    List<AnswerEntity> findAnswersByQuestionId(int questionId);
    AnswerEntity saveAnswer(AnswerEntity answer);
}
