package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("mainCategoryService")
    private CategoryService categoryService;
}
