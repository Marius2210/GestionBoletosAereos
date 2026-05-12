package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Tripulacion;

import java.util.List;
import java.util.Optional;

public interface TripulacionRepository extends JpaRepository<Tripulacion, Integer> {
    List<Tripulacion> findByAerolineaIdAerolinea(Integer idAerolinea);
    List<Tripulacion> findByCargoIgnoreCase(String cargo);
    Optional<Tripulacion> findByNombreIgnoreCase(String nombre);
}
