package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Service.Callback;

public interface ICategoryService {

    void create(Callback callback, Category category);

    void deleteById(Callback callback, Integer id);

    void getByMenuId(Callback callback, Integer id);
}
