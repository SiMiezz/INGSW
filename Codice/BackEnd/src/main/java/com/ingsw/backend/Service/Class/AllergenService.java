package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Allergen;
import com.ingsw.backend.Repository.AllergenRepository;
import com.ingsw.backend.Service.Interface.IAllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mainAllergenService")
public class AllergenService implements IAllergenService{

    @Autowired
    private AllergenRepository allergenRepository;
    public AllergenService() {
    }

    @Override
    public Allergen create(Allergen allergen){
        return allergenRepository.save(allergen);
    }

    @Override
    public List<Allergen> getAll(){
        return (List<Allergen>) allergenRepository.findAll();
    }

}
