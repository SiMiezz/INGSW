package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.CategoryDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryDAO categoryDAO = new CategoryDAO();
}
