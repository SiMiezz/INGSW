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
    //serve perchè c'è il problemma del nome della tabella order (va chiamata manualmente ratatouille.order)


    @Query(value = "DELETE FROM ordine WHERE id_order = :id;", nativeQuery = true)
    void delete(@PathVariable("id") Integer id);


}
