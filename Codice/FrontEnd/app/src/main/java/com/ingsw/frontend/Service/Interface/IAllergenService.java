package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Service.Callback;

public interface IAllergenService {

    void create(Callback callback, Allergen allergen);

    void getAll(Callback callback);
}
