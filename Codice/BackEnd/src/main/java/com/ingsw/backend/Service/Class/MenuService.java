package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.MenuRepository;
import com.ingsw.backend.Service.Interface.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainMenuService")
public class MenuService implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    public MenuService() {
    }
}
