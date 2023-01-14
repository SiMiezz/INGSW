package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Service.Interface.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("mainCategoryService")
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = categoryService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public List<CategoryDTO> getByMenuId(@PathVariable Integer id){
        List<Category> categoryList = categoryService.getByMenuId(id);

        List<CategoryDTO> categoryDTOS = new ArrayList<CategoryDTO>();
        for (Category category: categoryList) {
            categoryDTOS.add(convertDTO(category));
        }

        return categoryDTOS;
    }

    private CategoryDTO convertDTO(Category category) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO = modelMapper.map(category, CategoryDTO.class);

        Integer menu_id = category.getMenu().getId();
        categoryDTO.setMenuId(menu_id);
        return categoryDTO;
    }

}
