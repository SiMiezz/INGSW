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
}
