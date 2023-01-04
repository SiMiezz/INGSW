package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,String> {
}
