package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import com.ingsw.backend.Repository.CategoryRepository;
import com.ingsw.backend.Service.Interface.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("mainCategoryService")
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService() {
    }

    @Override
    public Category create(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(Integer id){
       Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            return false;
        }

        categoryRepository.delete(optionalCategory.get());
        return true;
    }

    @Override
    public List<Category> getByMenuId(Integer id){
        return categoryRepository.findByMenuId(id);
    }

    @Override
    public Optional<Category> getById(Integer id){
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getByMenuIdAndAliment(Integer id, Aliment_Type aliment_type){
        return categoryRepository.getCategoriesByMenuIdAndAliment(id, aliment_type);
    }
}
