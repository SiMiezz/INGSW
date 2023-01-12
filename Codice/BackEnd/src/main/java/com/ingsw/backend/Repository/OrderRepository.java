package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    //public List<Order> findByTablerestaurantId(Integer id);
}
