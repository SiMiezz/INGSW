package com.natour.Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.natour.Server.Model.Tappa;

@Repository
public interface TappaRepository extends JpaRepository<Tappa,Long> {
	
	@Query(value = "SELECT *"
			+ " FROM tappa t"
			+ " WHERE t.id_itinerario = :id_itinerario ORDER BY sequenza", nativeQuery = true)
	List<Tappa> findByItinerario(@Param(value = "id_itinerario") long id_itinerario);
	
}
