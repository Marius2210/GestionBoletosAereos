package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Aerolinea;

import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Integer> {
    Optional<Aerolinea> findByCodigoIata(String codigoIata);
    boolean existsByCodigoIata(String codigoIata);
}
