package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.TableRestaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRestaurantRepository extends CrudRepository<TableRestaurant,Integer> {
}
