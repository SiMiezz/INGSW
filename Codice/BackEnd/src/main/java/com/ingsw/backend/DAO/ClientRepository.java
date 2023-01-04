package com.example.provaing.repository;

import com.example.provaing.model.Client;
import com.example.provaing.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Integer> { //da correggere (dipende da PK)
}
