package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.udb.GestionBoletosAereos.model.Vuelo;
import java.time.LocalDateTime;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    List<Vuelo> findByAvionIdAvion(Integer idAvion);
    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino " +
            "AND DATE(v.fechaSalida) = DATE(:fechaSalida) AND v.estado != 'C'")
    List<Vuelo> findVuelosDisponibles(@Param("origen") String origen,
                                      @Param("destino") String destino,
                                      @Param("fechaSalida") LocalDateTime fechaSalida);
}