package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Element;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ElementRepository extends CrudRepository<Element,Integer> {

    List<Element> findByCategoryIdOrderByName(Integer id);

    @Query(value = "SELECT e.* " +
                   "FROM element AS e JOIN category AS c ON e.category_id = c.id_category " +
                   "WHERE c.menu_id = :id", nativeQuery = true)
    List<Element> findElementByMenuId(@Param(value = "id") Integer id);
}
