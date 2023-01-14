package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Element;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends CrudRepository<Element,Integer> {

    List<Element> findByCategoryId(Integer id);

    @Query(value = "SELECT e.* " +
            "FROM element AS e " +
            "JOIN category AS c ON e.category_id = c.id_category " +
            "JOIN menu AS m ON c.menu_id=m.id_menu " +
            "JOIN restaurant AS r ON m.restaurant_name = r.name " +
            "WHERE c.id_category= :idCategory AND r.name= :restaurantName", nativeQuery = true)
    List<Element> findByCategoryIdAndRestaurantId(@Param(value = "restaurantName") String restaurantName,
                                                  @Param(value = "idCategory") Integer idCategory);
}
