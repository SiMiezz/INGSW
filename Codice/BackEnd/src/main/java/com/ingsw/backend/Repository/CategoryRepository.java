package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    public List<Category> findByMenuQrCode(String menu);
}
