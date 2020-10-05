
package com.testonline.service;

import com.testonline.entity.CategoryEntity;
import java.util.List;


public interface ICategoryService {
    List<CategoryEntity> findListCategoryByUserId(int userId);
    
    void saveNewCategory(CategoryEntity category);
}
