
package com.testonline.service;

import com.testonline.entity.CategoryEntity;
import java.util.List;


public interface ICategoryService {
    List<CategoryEntity> findListCategoryByUserId(int userId);
    List<CategoryEntity> findListCategoryAndListQuestionByUserId(int userId);
    List<CategoryEntity> findListCategoryAndListQuestionByUserIdAndExamId(int userId,int examId);
    void saveNewCategory(CategoryEntity category);
}
