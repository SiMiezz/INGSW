package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Service.Interface.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    @Qualifier("mainCategoryService")
    private ICategoryService categoryService;

    @PostMapping("/category")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable Integer id){
        Boolean delete = categoryService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{code}")
    public List<Category> getByMenuQrCode(@PathVariable String code){
        return categoryService.getByMenuQrCode(code);
    }

}
