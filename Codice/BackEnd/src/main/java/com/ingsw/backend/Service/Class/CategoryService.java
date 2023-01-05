package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.CategoryRepository;
import com.ingsw.backend.Service.Interface.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainCategoryService")
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService() {
    }
}
