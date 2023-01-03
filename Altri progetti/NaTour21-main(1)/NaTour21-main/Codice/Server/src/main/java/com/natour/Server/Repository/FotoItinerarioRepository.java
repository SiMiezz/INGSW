package com.natour.Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.natour.Server.Model.FotoItinerario;

@Repository
public interface FotoItinerarioRepository extends JpaRepository<FotoItinerario,Long> {
	
	@Query(value = "SELECT * FROM fotoitinerario f WHERE f.id_itinerario = :idItinerario", nativeQuery = true)
	List<FotoItinerario> findByItinerario(@Param(value = "idItinerario") long idItinerario);
	
	@Query(value = "SELECT COUNT(*) FROM fotoitinerario f WHERE f.id_itinerario = :idItinerario", nativeQuery = true)
	long getCount(@Param(value = "idItinerario") long idItinerario);
}
