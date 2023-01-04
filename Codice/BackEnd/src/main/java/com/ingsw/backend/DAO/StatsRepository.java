package com.example.provaing.repository;

import com.example.provaing.model.Stats;
import com.example.provaing.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends CrudRepository<Stats,Integer> { //da modificare (dipende da PK)
}
