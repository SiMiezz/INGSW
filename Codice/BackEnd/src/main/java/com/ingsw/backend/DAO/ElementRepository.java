package com.example.provaing.repository;

import com.example.provaing.model.Element;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<Element,Integer> {
}
