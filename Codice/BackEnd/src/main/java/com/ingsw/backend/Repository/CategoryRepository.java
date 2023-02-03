package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    List<Category> findByMenuId(Integer idMenu);

    List<Category> findByMenuIdAndAlimentOrderByPosizione(Integer id, Aliment_Type aliment_type);

     @Query(value = "SELECT * FROM category WHERE menu_id = :id ORDER BY aliment,posizione", nativeQuery = true)
    List<Category> findCategoryByMenuIdOrderByAlimentAndPosition(@Param(value = "id") Integer id);
}
