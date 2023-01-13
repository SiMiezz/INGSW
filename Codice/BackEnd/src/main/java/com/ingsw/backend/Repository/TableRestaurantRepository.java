package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.TableRestaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRestaurantRepository extends CrudRepository<TableRestaurant,Integer> {

    List<TableRestaurant> findByRestaurantName(String name);
}
