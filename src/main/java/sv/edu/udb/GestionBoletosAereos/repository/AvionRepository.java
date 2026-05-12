package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Avion;

import java.util.List;

public interface AvionRepository extends JpaRepository<Avion, Integer> {
    List<Avion> findByAerolineaIdAerolinea(Integer idAerolinea);
}
