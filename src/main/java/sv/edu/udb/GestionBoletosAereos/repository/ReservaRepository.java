package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByPasajeroIdPasajero(Integer idPasajero);
    Optional<Reserva> findByCodigoReserva(String codigoReserva);
    List<Reserva> findByVueloIdVuelo(Integer idVuelo);
}
