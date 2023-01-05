package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Menu;
import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Repository.MenuRepository;
import com.ingsw.backend.Service.Interface.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainMenuService")
public class MenuService implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    public MenuService() {
    }

    @Override
    public Optional<Menu> getByRestaurant(Restaurant restaurant){
        return menuRepository.findByRestaurant(restaurant);
    }
}
