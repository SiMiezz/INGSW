package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Allergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen,String> {
}
