package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Category;

public interface ICategoryService {

    public Category create(Category category);

    public Boolean deleteById(int id);
}
