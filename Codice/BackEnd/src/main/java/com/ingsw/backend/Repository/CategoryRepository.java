package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    List<Category> findByMenuId(Integer idMenu);

    List<Category> getCategoriesByMenuIdAndAliment(Integer id, Aliment_Type aliment_type);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM category " +
                    "WHERE id_category = :category_id", nativeQuery = true)
    void deleteByCategoryId(@Param(value = "category_id") Integer category_id);



}
