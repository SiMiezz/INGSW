package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Allergen;
import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.AllergenDTO;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Service.Interface.IAllergenService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/allergen")
public class AllergenController {

    @Autowired
    @Qualifier("mainAllergenService")
    private IAllergenService allergenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Allergen create(@RequestBody Allergen allergen){
        return allergenService.create(allergen);
    }

    @GetMapping("/get")
    public List<AllergenDTO> getAll(){
        List<Allergen> allergenList = allergenService.getAll();

        List<AllergenDTO> allergenDTOS = new ArrayList<>();
        for (Allergen allergen: allergenList) {
            allergenDTOS.add(convertDTO(allergen));
        }

        return allergenDTOS;
    }

    private AllergenDTO convertDTO(Allergen allergen) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AllergenDTO allergenDTO = new AllergenDTO();
        allergenDTO = modelMapper.map(allergen, AllergenDTO.class);

        return allergenDTO;
    }

}
