package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Pasajero;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {
    Optional<Pasajero> findByNumPasaporte(String numPasaporte);
    Optional<Pasajero> findByEmail(String email);
}
