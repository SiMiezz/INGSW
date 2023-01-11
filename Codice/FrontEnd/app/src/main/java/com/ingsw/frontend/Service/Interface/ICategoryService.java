package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Category;

public interface ICategoryService {

    void create(Category category);

    void deleteById(Integer id);

    void getByMenuQrCode(String code);
}
