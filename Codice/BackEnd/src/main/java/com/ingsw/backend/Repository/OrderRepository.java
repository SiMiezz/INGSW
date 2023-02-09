package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Query(value = "SELECT * FROM ordine AS o " +
                   "WHERE o.table_id = :id", nativeQuery = true)
    List<Order> findByTableId(@PathVariable("id") Integer id);

    @Query(value = "SELECT SUM(o.price) FROM ordine AS o " +
            "WHERE o.table_id = :id", nativeQuery = true)
    Double sumPriceByTableId(@PathVariable("id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM ordine AS o " +
            "WHERE o.table_id = :id", nativeQuery = true)
    Long countByTableId(@PathVariable("id") Integer id);

//    @Query(value = "SELECT COUNT(*) FROM composed AS c WHERE c.element_id = :id", nativeQuery = true)
//    Integer getCountElementOrdered(@PathVariable("id") Integer id);



    // QUERY DA IMPLEMENTARE DOPO (testata e funzionante su mysql)

    @Query(value = "SELECT COUNT(*) FROM composed AS c join ordine AS o ON c.order_id=o.id_order " +
            "WHERE c.element_id = :id AND (o.datecreate BETWEEN :date_from AND :date_to)" , nativeQuery = true)
    Integer getCountElementOrdered(
            @PathVariable("id") Integer id, @PathVariable("date_from") Date date_from, @PathVariable("date_to") Date date_to);


}
