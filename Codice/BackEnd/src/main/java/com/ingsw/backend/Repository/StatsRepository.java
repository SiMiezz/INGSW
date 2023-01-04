package com.ingsw.backend.Repository;

import com.ingsw.backend.Model.Stats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends CrudRepository<Stats,Integer> { //da modificare (dipende da PK)
}
