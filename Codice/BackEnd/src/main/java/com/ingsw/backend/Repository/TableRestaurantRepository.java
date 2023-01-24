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

    Long countTableRestaurantByRestaurantName(String name);

    @Query(value = "SELECT seats FROM tablerestaurant AS t WHERE t.id_table = :id", nativeQuery = true)
    Integer getSeatsByTableRestaurantId(@Param("id") Integer id);


}
