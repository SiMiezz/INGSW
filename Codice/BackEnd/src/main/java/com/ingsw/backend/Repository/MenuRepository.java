package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<Menu,Integer> {

    public Optional<Menu> findByRestaurantName(String name);
}
