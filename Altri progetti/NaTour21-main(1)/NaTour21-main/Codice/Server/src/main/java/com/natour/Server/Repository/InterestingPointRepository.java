package com.natour.Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.natour.Server.Model.InterestingPoint;

@Repository
public interface InterestingPointRepository extends JpaRepository<InterestingPoint,Long> {
	
	@Query(value = "SELECT *"
			+ " FROM interestingpoint i"
			+ " WHERE i.id_itinerario = :id_itinerario", nativeQuery = true)
	List<InterestingPoint> findByItinerario(@Param(value = "id_itinerario") long id_itinerario);
	
}
