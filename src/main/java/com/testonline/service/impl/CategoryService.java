package com.testonline.service.impl;

import com.testonline.entity.CategoryEntity;
import com.testonline.repository.CategoryRepository;
import com.testonline.service.ICategoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRP;

    @Autowired
    QuestionService questionSV;
    @Override
    public List<CategoryEntity> findListCategoryByUserId(int userId) {
        return categoryRP.findListCategoryByUserId(userId);
    }

    @Override
    public void saveNewCategory(CategoryEntity category) {
        categoryRP.save(category);
    }

    @Override
    public List<CategoryEntity> findListCategoryAndListQuestionByUserId(int userId) {
        List<CategoryEntity> listCategory = categoryRP.findListCategoryByUserId(userId);
        List<CategoryEntity> listCategoryTemp = new ArrayList<CategoryEntity>();
        for (CategoryEntity categoryEntity : listCategory) {
            categoryEntity.setListQuestion(questionSV.findQuestionByCategoryId(categoryEntity.getCategoryId()));
            listCategoryTemp.add(categoryEntity);
        }

        return listCategoryTemp;
    }

    @Override
    public List<CategoryEntity> findListCategoryAndListQuestionByUserIdAndExamId(int userId, int examId) {
       
        List<CategoryEntity> listCategory = categoryRP.findListCategoryByUserId(userId);
        List<CategoryEntity> listCategoryTemp = new ArrayList<CategoryEntity>();
        for (CategoryEntity categoryEntity : listCategory) {
            categoryEntity.setListQuestion(questionSV.findQuestionByCategoryIdAndExamIdAndNotInQuestionRandom(categoryEntity.getCategoryId(),examId));
            listCategoryTemp.add(categoryEntity);
        }

        return listCategoryTemp;
    }

}
