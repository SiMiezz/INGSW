package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;

import java.util.List;

public interface ICategoryService {

    Category create(Category category);

    Boolean deleteById(Integer id);

    List<Category> getByMenuId(Integer id);

    List<Category> getByMenuIdAndAliment(Integer id, Aliment_Type aliment_type);
}
