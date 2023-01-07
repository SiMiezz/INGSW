package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String>{

    public List<User> findByRestaurantName(String name);
}
