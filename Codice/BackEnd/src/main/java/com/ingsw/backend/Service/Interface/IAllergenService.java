package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Allergen;

import java.util.List;

public interface IAllergenService {

    public Allergen create(Allergen allergen);

    public List<Allergen> getAll();
}
