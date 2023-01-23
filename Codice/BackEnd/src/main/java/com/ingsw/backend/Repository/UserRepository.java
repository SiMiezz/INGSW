package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String>{

    List<User> findByRestaurantName(String name);

    Optional<User> findByEmailAndPwd(String email, String pwd);

    List<User> findByRestaurantNameAndJob(String restaurant_name, User_Type job);

}

