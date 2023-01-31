package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Allergen;
import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.AllergenDTO;
import com.ingsw.backend.Model.DTO.ElementDTO;
import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Service.Interface.IAllergenService;
import com.ingsw.backend.Service.Interface.ICategoryService;
import com.ingsw.backend.Service.Interface.IElementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    @Qualifier("mainElementService")
    private IElementService elementService;

    @Autowired
    @Qualifier("mainCategoryService")
    private ICategoryService categoryService;

    @Autowired
    @Qualifier("mainAllergenService")
    private IAllergenService allergenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public void create(@RequestBody ElementDTO elementDTO){
        Element element = this.convertEntity(elementDTO);

        elementService.create(element);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ElementDTO elementDTO){
        Element element = this.convertEntity(elementDTO);

        elementService.delete(element);
    }

    @GetMapping("/get/{id}")
    public List<ElementDTO> getByCategoryId(@PathVariable Integer id){
        List<Element> elementList = elementService.getByCategoryId(id);

        List<ElementDTO> elementDTOS = new ArrayList<>();
        for (Element element: elementList) {
            elementDTOS.add(convertDTO(element));
        }

        return elementDTOS;
    }

    private ElementDTO convertDTO(Element element) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ElementDTO elementDTO = new ElementDTO();
        elementDTO = modelMapper.map(element, ElementDTO.class);

        Integer category_id = element.getCategory().getId();
        elementDTO.setCategoryId(category_id);

        return elementDTO;
    }

    private Element convertEntity(ElementDTO elementDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Element element = new Element();
        element = modelMapper.map(elementDTO, Element.class);

        //Mapping
        Integer id = elementDTO.getCategoryId();
        Optional<Category> categoryOptional = this.categoryService.getById(id);

        List<Allergen> allergenList = new ArrayList<>();

        if(elementDTO.getAllergens() != null){
            for (AllergenDTO allergenDTO:elementDTO.getAllergens()) {
                allergenList.add(convertAllergen(allergenDTO));
            }
        }

        if(!categoryOptional.isEmpty()){
            element.setCategory(categoryOptional.get());
        }
        element.setAllergenList(allergenList);

        return element;
    }

    private Allergen convertAllergen(AllergenDTO allergenDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Allergen allergen = new Allergen();
        allergen = modelMapper.map(allergenDTO, Allergen.class);

        return allergen;
    }
}
