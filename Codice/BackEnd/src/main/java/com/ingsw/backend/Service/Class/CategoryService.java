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
    public void update(Category category){
        categoryRepository.save(category);
    }

    @Override
    public void create(Category category){
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category){
        categoryRepository.delete(category);
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
        return categoryRepository.findByMenuIdAndAlimentOrderByPosizione(id, aliment_type);
    }

    @Override
    public List<Category> getCategoryByMenuIdOrderByAlimentAndPosition(Integer id) {
        return categoryRepository.findCategoryByMenuIdOrderByAlimentAndPosition(id);
    }
}
