package com.example.provaing.repository;

import com.example.provaing.model.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends CrudRepository<Tables,Integer> {
}
