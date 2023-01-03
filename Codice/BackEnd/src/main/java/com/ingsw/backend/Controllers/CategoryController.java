package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.CategoryDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryDAO categoryDAO = new CategoryDAO();
}
