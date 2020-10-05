
package com.testonline.service.impl;

import com.testonline.entity.CategoryEntity;
import com.testonline.repository.CategoryRepository;
import com.testonline.service.ICategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    CategoryRepository categoryRP;

    @Override
    public List<CategoryEntity> findListCategoryByUserId(int userId) {
        return categoryRP.findListCategoryByUserId(userId);
    }

    @Override
    public void saveNewCategory(CategoryEntity category) {
        categoryRP.save(category);
    }
    
}
