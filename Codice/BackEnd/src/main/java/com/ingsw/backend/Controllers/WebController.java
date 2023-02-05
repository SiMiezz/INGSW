package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Service.Interface.ICategoryService;
import com.ingsw.backend.Service.Interface.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    @Qualifier("mainCategoryService")
    private ICategoryService categoryService;

    @Autowired
    @Qualifier("mainElementService")
    private IElementService elementService;

    @GetMapping("/qrcode")
    public String getById(@RequestParam(name = "id") Integer id, Model model){
        List<Category> categoryList = categoryService.getCategoryByMenuIdOrderByAlimentAndPosition(id);
        List<Element> elementListTotal = new ArrayList<>();

        model.addAttribute("id",id);
        model.addAttribute("categories",categoryList);

        for (Category category: categoryList) {
            List<Element> elementList = elementService.getByCategoryId(category.getId());
            elementListTotal.addAll(elementList);
        }

        model.addAttribute("elements",elementListTotal);

        return "qrcode";
    }
}
