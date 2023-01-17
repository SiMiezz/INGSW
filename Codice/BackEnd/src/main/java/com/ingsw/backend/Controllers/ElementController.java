package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Model.DTO.ElementDTO;
import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Service.Interface.IElementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    @Qualifier("mainElementService")
    private IElementService elementService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Element create(@RequestBody Element element){
        return elementService.create(element);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = elementService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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
}
