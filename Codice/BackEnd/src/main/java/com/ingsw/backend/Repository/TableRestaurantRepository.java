package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.TableRestaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRestaurantRepository extends CrudRepository<TableRestaurant,Integer> {

    List<TableRestaurant> findByRestaurantName(String name);

    @Query(value = "SELECT COUNT(*) FROM tablerestaurant r WHERE r.restaurant_name = :restaurant_name", nativeQuery = true)
    Integer countTotalTableByRestaurantName(@Param(value = "restaurant_name") String restaurant_name);
}
