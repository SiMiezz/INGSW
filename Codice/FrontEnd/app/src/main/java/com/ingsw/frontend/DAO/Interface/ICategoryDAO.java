package com.ingsw.frontend.DAO.Interface;

import com.ingsw.frontend.Model.Category;

public interface ICategoryDAO {

    void create(Category category);

    void deleteById(Integer id);

    void getByMenuQrCode(String code);
}
