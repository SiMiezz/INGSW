package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String>{
}
