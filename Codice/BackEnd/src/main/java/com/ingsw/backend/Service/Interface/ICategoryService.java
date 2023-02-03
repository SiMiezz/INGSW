package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    void update(Category category);

    void create(Category category);

    void delete(Category category);

    List<Category> getByMenuId(Integer id);

    Optional<Category> getById(Integer id);

    List<Category> getByMenuIdAndAliment(Integer id, Aliment_Type aliment_type);

    List<Category> getCategoryByMenuIdOrderByAlimentAndPosition(Integer id);
}
