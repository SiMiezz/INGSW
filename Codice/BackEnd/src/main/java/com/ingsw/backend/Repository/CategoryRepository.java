package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Query(value = "SELECT c.id_category, c.name, c.menu_id FROM category c WHERE c.menu_id = :idMenu", nativeQuery = true)
    List<Category> findByMenuId(@Param(value = "idMenu") Integer idMenu);
}
