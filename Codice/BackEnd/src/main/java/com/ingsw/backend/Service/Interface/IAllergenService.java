package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Allergen;

import java.util.List;

public interface IAllergenService {

    Allergen create(Allergen allergen);

    List<Allergen> getAll();
}
