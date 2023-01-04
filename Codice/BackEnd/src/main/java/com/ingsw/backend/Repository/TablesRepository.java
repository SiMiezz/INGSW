package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends CrudRepository<Tables,Integer> {
}
