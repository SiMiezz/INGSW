package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu,String> {
}
