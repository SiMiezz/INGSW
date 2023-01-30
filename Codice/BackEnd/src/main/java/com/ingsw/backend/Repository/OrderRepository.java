package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Query(value = "SELECT * FROM ordine AS o " +
                   "WHERE o.table_id = :id", nativeQuery = true)
    List<Order> findByTableId(@PathVariable("id") Integer id);

    @Query(value = "SELECT SUM(o.price) FROM ordine AS o " +
            "WHERE o.table_id = :id", nativeQuery = true)
    Double sumPriceByTableId(@PathVariable("id") Integer id);

}
