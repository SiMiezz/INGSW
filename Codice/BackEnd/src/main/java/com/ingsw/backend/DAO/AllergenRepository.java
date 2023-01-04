package com.example.provaing.repository;

import com.example.provaing.model.Allergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen,String> {
}
