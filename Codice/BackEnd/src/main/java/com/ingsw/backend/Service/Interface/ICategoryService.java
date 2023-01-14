package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Category;
import java.util.List;

public interface ICategoryService {

    Category create(Category category);

    Boolean deleteById(Integer id);

    List<Category> getByMenuId(Integer id);
}
