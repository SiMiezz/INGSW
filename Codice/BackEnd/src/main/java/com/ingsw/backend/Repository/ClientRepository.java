package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Integer> { //da correggere (dipende da PK)
}
