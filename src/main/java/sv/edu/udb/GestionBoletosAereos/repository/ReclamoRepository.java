package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Reclamo;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    List<Reclamo> findByReservaIdReserva(Integer idReserva);
}
