package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    List<Category> findByMenuId(Integer idMenu);

    List<Category> getCategoriesByMenuIdAndAliment(Integer id, Aliment_Type aliment_type);

}
