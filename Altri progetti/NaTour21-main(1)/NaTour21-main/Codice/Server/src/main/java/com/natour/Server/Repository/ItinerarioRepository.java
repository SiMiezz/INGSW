package com.natour.Server.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.natour.Server.Model.Itinerario;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario,Long> {
	
	@Query(value = "SELECT * FROM itinerario i WHERE i.id_utente = :username", nativeQuery = true)
	List<Itinerario> findByUsername(@Param(value = "username") String username);
	
	@Query(value = "SELECT * FROM itinerario ORDER BY id_itinerario DESC", nativeQuery = true)
	List<Itinerario> findAllByRecent();
	
	@Query(value = "SELECT *"
			+ " FROM itinerario i"
			+ " WHERE UPPER(i.titolo) LIKE concat('%', UPPER(:nomeItinerario), '%')", nativeQuery = true)
	List<Itinerario> findAllByName(@Param(value = "nomeItinerario") String nomeItinerario);
	
	@Query(value = "SELECT max(i.id_itinerario) FROM itinerario i WHERE i.id_utente LIKE :username", nativeQuery = true)
	Optional<Long> getLastId(@Param(value = "username") String username);
	
	@Query(value = "SELECT *"
			+ " FROM itinerario i"
			+ " WHERE i.puntoinizio = :puntoiniziale"
			+ " AND i.latitudine_pi = :latitudineI"
			+ " AND i.longitudine_pi = :longitudineI"
			+ " AND i.puntofine = :puntofinale"
			+ " AND i.latitudine_pf = :latitudineF"
			+ " AND i.longitudine_pf = :longitudineF", nativeQuery = true)
	Optional<String> checkEquals(@Param(value = "puntoiniziale") String puntoiniziale,
									@Param(value = "latitudineI") double latitudineI,
									@Param(value = "longitudineI") double longitudineiI,
									@Param(value = "puntofinale") String puntofinale,
									@Param(value = "latitudineF") double latitudineF,
									@Param(value = "longitudineF") double longitudineF);
	
	@Query(value = "SELECT * FROM itinerario i WHERE UPPER(i.titolo) LIKE concat('%', UPPER(:nome), '%')"
					+" AND UPPER(i.puntoinizio) LIKE concat('%', UPPER(:puntoinizio), '%')"
					+" AND UPPER(i.puntofine) LIKE concat('%', UPPER(:puntofine), '%')"
					+" AND i.durata <= :durata"
					+" AND i.lunghezza <= :lunghezza"
					+" AND i.difficulty LIKE %:difficulty%"
					+" AND (i.accessodisabili = 'true' OR i.accessodisabili = :accessoDisabili)"
					+" AND i.areageografica LIKE %:areageografica%"
					+" ORDER BY id_itinerario DESC", nativeQuery = true)
	List<Itinerario> findByFilter(@Param(value = "nome") String nome,
									@Param(value = "puntoinizio") String puntoinizio,
									@Param(value = "puntofine") String puntofine,
									@Param(value = "durata") Timestamp durata,
									@Param(value = "lunghezza") double lunghezza,
									@Param(value = "difficulty") String difficulty,
									@Param(value = "accessoDisabili") boolean accessoDisabili,
									@Param(value = "areageografica") String areageografica);
	
}
