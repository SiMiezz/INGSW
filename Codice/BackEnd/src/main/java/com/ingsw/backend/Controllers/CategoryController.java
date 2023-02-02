package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import com.ingsw.backend.Model.Menu;
import com.ingsw.backend.Service.Interface.ICategoryService;
import com.ingsw.backend.Service.Interface.IMenuService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("mainCategoryService")
    private ICategoryService categoryService;

    @Autowired
    @Qualifier("mainMenuService")
    private IMenuService menuService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/update")
    public void update(@RequestBody CategoryDTO categoryDTO){
        Category category = this.convertEntity(categoryDTO);

        categoryService.update(category);
    }

    @PostMapping("/create")
    public void create(@RequestBody CategoryDTO categoryDTO){
        Category category = this.convertEntity(categoryDTO);

        categoryService.create(category);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody CategoryDTO categoryDTO){
        Category category = this.convertEntity(categoryDTO);

        categoryService.delete(category);
    }

    @GetMapping("/get/{id}")
    public List<CategoryDTO> getByMenuId(@PathVariable Integer id){
        List<Category> categoryList = categoryService.getByMenuId(id);

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category: categoryList) {
            categoryDTOS.add(convertDTO(category));
        }

        return categoryDTOS;
    }

    @GetMapping("/get/{id}/{aliment_type}")
    public List<CategoryDTO> getByMenuIdAndAliment(@PathVariable Integer id, @PathVariable Aliment_Type aliment_type){
        List<Category> categoryList = categoryService.getByMenuIdAndAliment(id,aliment_type);

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
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

    private Category convertEntity(CategoryDTO categoryDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Category category = new Category();
        category = modelMapper.map(categoryDTO, Category.class);

        //Mapping
        Integer id = categoryDTO.getMenuId();
        Optional<Menu> menuOptional = this.menuService.getById(id);

        if(!menuOptional.isEmpty()){
            category.setMenu(menuOptional.get());
        }

        return category;
    }

}
